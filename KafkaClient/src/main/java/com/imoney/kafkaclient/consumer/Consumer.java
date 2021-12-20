package com.imoney.kafkaclient.consumer;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface Consumer<K, V> extends Runnable {

	void subscriber(ConsumerRecords<K, V> records);

	void configure(Properties properties);

}
