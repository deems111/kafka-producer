package com.example.kafkaproducer.kafkaP;


import com.example.kafkaproducer.data.AbstractEvent;

/**
 * Service For Sending Messages To Kafka
 */
public interface KafkaProducerService {
    void send(AbstractEvent event);
}
