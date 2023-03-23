package com.example.kafkaproducer.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaProducerConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServerAddress;

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        var factory = new DefaultKafkaProducerFactory<String, String>(kafkaProducerProperties());
        return new KafkaTemplate<>(factory);
    }

    @Bean
    public Map<String, Object> kafkaProducerProperties() {
        return Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerAddress,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.RETRIES_CONFIG, 3,
                ProducerConfig.BATCH_SIZE_CONFIG, 0,
                ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 0,
                ProducerConfig.LINGER_MS_CONFIG, 0
        );
    }
}
