package com.imoney.kafkaclient.utill;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

	public static final String BOOTSTRAP_SERVERS = "bootstrap.servers";

	@UtilityClass
	public class Consumer {

		public static final String GROUP_ID = "group.id";
		public static final String ENABLE_AUTO_COMMIT = "enable.auto.commit";
		public static final String AUTO_COMMIT_INTERVAL_MS = "auto.commit.interval.ms";
		public static final String SESSION_TIMEOUT_MS = "session.timeout.ms";
		public static final String KEY_DESERIALIZER = "key.deserializer";
		public static final String VALUE_DESERIALIZER = "value.deserializer";
		public static final String CLIENT_ID = "client.id";
		public static final String AUTO_OFFSET_RESET = "auto.offset.reset";
		public static final String MAX_PARTITION_FETCH_BYTES = "max.partition.fetch.bytes";
	}

	@UtilityClass
	public class Producer {

		public static final String REQUEST_REQUIRED_ACKS = "request.required.acks";
		public static final String KEY_SERIALIZER = "key.serializer";
		public static final String VALUE_SERIALIZER = "value.serializer";
		public static final String NUMBER_OF_PARTITIONS = "num.partitions";
	}

}