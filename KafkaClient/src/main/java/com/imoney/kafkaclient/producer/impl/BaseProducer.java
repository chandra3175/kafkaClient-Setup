package com.imoney.kafkaclient.producer.impl;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import com.imoney.kafkaclient.producer.Publisher;

public abstract class BaseProducer<K, V> implements Publisher {

	protected Properties properties = null;
	protected Producer<K, V> producer = null;

	public abstract void publish(String topic, Object payload);

	@Override
	public void configure(Properties properties) {
		this.properties = properties;
		this.producer = new KafkaProducer<>(properties);
	}

}