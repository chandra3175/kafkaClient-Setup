/**
 * 
 */
package com.imoney.kafkaclient.producer.impl;

/**
 * @author naveen
 *
 * @date 10-Mar-2017
 */
public abstract class BasePartitionProducer<K, V> extends BaseProducer<String, String> {

	/**
	 * Method to publish record on Kafka on a specific partition
	 * 
	 * @param topic
	 * @param payload
	 * @param partition
	 */
	public abstract void publish(String topic, Object payload, int partition);

}