# Kafka Producer Test Application 
Author: deemsy - https://github.com/deems111

### Description
Java 17 / Spring Boot 3 Application: 
generate random strings messages and send to Kafka, one message = one string.
Simulate delay of Sending to Kafka using application.properties.

### Tech stack
      * Java 17
      * Spring Boot 3
      * Kafka 3
Other  
   * Maven / Docker (for Kafka)
### Before Using
0) (Optional) For creating topic - uncomment annotations in KafkaTopicsConfig and define topic name in application.properties*.
1) Update Kafka Property in application.properties*:
   * spring.kafka.bootstrap-servers
   * kafka.topic.name
2) (Optional) Update Word Generation Properties in application.properties:
   * generator.word.useLetters
   * generator.word.useNumbers
   * generator.word.length=100
3) (Optional) Update Word Generation Delay in application.properties:
   * generator.word.delay.ms
4) (Optional) Update Logging Level in application.properties:
   * logging.level.com.example.kafkaproducer
5) Check Application Port in application.properties:
   * server.port
6) (Optional) Use docker-compose (launch zookeeper / kafka) - check ports.

*- Create topic:


<code>
kafka-topics.sh --bootstrap-server SERVER_ADDRESS --topic TOPIC_NAME --create --partitions 1 --replication-factor 1
</code>