package com.imoney.kafkaclient.consumer.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.context.request.RequestContextHolder;

import com.google.gson.Gson;
import com.imoney.kafkaclient.consumer.Consumer;
import com.imoney.kafkaclient.utill.CustomKafkaRequestScopeAttr;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableKafka
public abstract class BaseConsumer<K, V> implements Consumer<K, V> {

	@SuppressWarnings("rawtypes")
	public static final List<BaseConsumer> consumers = new CopyOnWriteArrayList<>();

	@Autowired
	Gson gson;

	Properties properties = null;
	KafkaConsumer<K, V> consumer = null;
	List<String> topics = null;

	private AtomicBoolean shutdown;
	private CountDownLatch shutdownLatch;

	public abstract List<String> getTopics();

	public abstract int getPollDuration();

	public BaseConsumer() {
		consumers.add(this);
	}

	@Override
	public void configure(Properties properties) {
		this.properties = properties;
		this.consumer = new KafkaConsumer<>(properties);
		this.consumer.subscribe(getTopics());
		this.shutdown = new AtomicBoolean(false);
		this.shutdownLatch = new CountDownLatch(1);
	}

	@Override
	public void run() {
		try {
			while (!shutdown.get()) {
				ConsumerRecords<K, V> records = this.consumer.poll(getPollDuration());

				if (records != null && !records.isEmpty()) {

					RequestContextHolder.setRequestAttributes(new CustomKafkaRequestScopeAttr());

					Iterator<ConsumerRecord<K, V>> kafkaRecord = records.iterator();
					ConsumerRecord<String, Object> row = (ConsumerRecord<String, Object>) kafkaRecord.next();

					String response = row.value().toString();
					JSONObject obj = null;

					if (response.startsWith("[")) {
						JSONArray array = new JSONArray(response);
						obj = array.getJSONObject(0);
					} else {
						obj = new JSONObject(response);
					}
					// if (obj.has("tenantId") && obj.getString("tenantId") != null) {
//					if (obj.has("tenantId") && obj.optString("tenantId", null) != null) {
//						log.info(this.getClass().getName() + "consumer | topic -" + getTopics() + "| tenant id -"
//								+ obj.getString("tenantId"));
//						TenantContextHelper.setTenantId(obj.getString("tenantId"));
//
//					} else {
//						log.info(this.getClass().getName() + "consumer | topic -" + getTopics() + "consumer | dto -"
//								+ obj.toString() + "| setting default tenant id");
//
//						TenantContextHelper.setTenantId(tenantID);
//					}
					subscriber(records);
					log.info(getTopics() + " size : " + records.count() + " processed");

					commit();
				}
			}
		} catch (Exception e) {
			log.error("Exception while parsing json : ", e);
		} finally {
			RequestContextHolder.resetRequestAttributes();
			consumer.close();
			shutdownLatch.countDown();
			log.info(this.getClass().getName() + " Consumer exited");
		}
	}

	private void commit() {
		try {
			this.consumer.commitSync();
		} catch (Exception e) {
			log.error("commit api get failed : ", e);
		}
	}

	public boolean shutdown() throws InterruptedException {
		// Check if shutdown is already in process
		if (shutdownLatch.getCount() == 0) {
			return false;
		}
		shutdown.set(true);
		shutdownLatch.await();
		return true;
	}

	@SuppressWarnings("rawtypes")
	public static List<BaseConsumer> getConsumers() {
		return consumers;
	}

}
