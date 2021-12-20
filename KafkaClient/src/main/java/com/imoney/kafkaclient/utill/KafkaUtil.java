package com.imoney.kafkaclient.utill;

import java.util.Properties;

import org.springframework.core.env.Environment;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class KafkaUtil {


	public static Properties getProducerProperties(Environment environment) {
		Properties prop = new Properties();

		prop.put(Constants.BOOTSTRAP_SERVERS, getProperty(environment, Constants.BOOTSTRAP_SERVERS));
		prop.put(Constants.Producer.KEY_SERIALIZER, getProperty(environment, Constants.Producer.KEY_SERIALIZER));
		prop.put(Constants.Producer.VALUE_SERIALIZER, getProperty(environment, Constants.Producer.VALUE_SERIALIZER));
		prop.put(Constants.Producer.REQUEST_REQUIRED_ACKS, getProperty(environment, Constants.Producer.REQUEST_REQUIRED_ACKS));
		prop.put(Constants.Producer.NUMBER_OF_PARTITIONS, getProperty(environment, Constants.Producer.NUMBER_OF_PARTITIONS));

		return prop;
	}

	public static Properties getConsumerProperties(Environment environment) {
		Properties properties = new Properties();

		properties.put(Constants.BOOTSTRAP_SERVERS, getProperty(environment, Constants.BOOTSTRAP_SERVERS));
		properties.put(Constants.Consumer.KEY_DESERIALIZER, getProperty(environment, Constants.Consumer.KEY_DESERIALIZER));
		properties.put(Constants.Consumer.VALUE_DESERIALIZER, getProperty(environment, Constants.Consumer.VALUE_DESERIALIZER));
		properties.put(Constants.Consumer.GROUP_ID, getProperty(environment, Constants.Consumer.GROUP_ID));
		properties.put(Constants.Consumer.ENABLE_AUTO_COMMIT, getProperty(environment, Constants.Consumer.ENABLE_AUTO_COMMIT));
		properties.put(Constants.Consumer.AUTO_COMMIT_INTERVAL_MS, getProperty(environment, Constants.Consumer.AUTO_COMMIT_INTERVAL_MS));
		properties.put(Constants.Consumer.SESSION_TIMEOUT_MS, getProperty(environment, Constants.Consumer.SESSION_TIMEOUT_MS));
		properties.put(Constants.Consumer.MAX_PARTITION_FETCH_BYTES, 21943040);

		return properties;
	}

	private static String getProperty(Environment environment, String key) {
		String value = environment.getProperty(key);
		log.debug("Loading Key => " + key + ", value => " + value);
		return value;
	}

}