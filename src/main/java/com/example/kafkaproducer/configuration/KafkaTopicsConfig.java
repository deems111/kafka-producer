package com.example.kafkaproducer.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Map;

/**
 * Configuration for topic creation
 */
//@Configuration
public class KafkaTopicsConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServerAddress;

    @Value(value = "${kafka.topic.create.name}")
    private String topicName;

    //@Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerAddress));
    }

    //@Bean
    public NewTopic newTopic() {
        return TopicBuilder.name(topicName)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
