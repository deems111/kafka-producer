package com.example.kafkaproducer.kafkaP.impl;

import com.example.kafkaproducer.data.AbstractEvent;
import com.example.kafkaproducer.kafkaP.KafkaProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Sending To Kafka
     * 1) Serialization
     * 2) Send to Kafka
     * 3) Logging result
     */
    @SneakyThrows
    @Override
    public void send(AbstractEvent event) {
        String eventJson = objectMapper.writeValueAsString(event);

        var topicName = event.getTopicName();
        var eventId = event.getEventId();
        kafkaTemplate.send(topicName, eventId, eventJson)
                //addCallback not exists in SpringBoot 3 :(
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Message with id = {} successfully sent to topic {}", eventId, topicName);
                        log.debug("Message data {}, offset = {}, partition = {}", eventJson, result.getRecordMetadata().offset(),
                                result.getRecordMetadata().partition());
                    } else {
                        //TODO - handle exception - save the message and resend
                        log.error("Error while sending message {} to topic {} - {}", eventJson, topicName, ex.getMessage());
                    }
                });
    }

}
