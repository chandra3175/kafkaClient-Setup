# kafkaClient-Setup
Kafka-client-setup

STEP 1: GET KAFKA
Download the latest Kafka release and extract it:

$ tar -xzf kafka_2.13-3.0.0.tgz
$ cd kafka_2.13-3.0.0

STEP 2: START THE KAFKA ENVIRONMENT

# Start the ZooKeeper service
# Note: Soon, ZooKeeper will no longer be required by Apache Kafka.
$ bin/zookeeper-server-start.sh config/zookeeper.properties

Open another terminal session and run:

# Start the Kafka broker service
$ bin/kafka-server-start.sh config/server.properties

STEP 3: CREATE A TOPIC TO STORE YOUR EVENTS

$ bin/kafka-topics.sh --create --partitions 1 --replication-factor 1 --topic user_detail --bootstrap-server localhost:9092


$ bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092
Topic:quickstart-events  PartitionCount:1    ReplicationFactor:1 Configs:
    Topic: quickstart-events Partition: 0    Leader: 0   Replicas: 0 Isr: 0
    
    
STEP 4: WRITE SOME EVENTS INTO THE TOPIC
$ bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
This is my first event
This is my second event

STEP 5: READ THE EVENTS

$ bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
This is my first event
This is my second event


IMPORTANT LINK: https://kafka.apache.org/quickstart#quickstart_startserver 
