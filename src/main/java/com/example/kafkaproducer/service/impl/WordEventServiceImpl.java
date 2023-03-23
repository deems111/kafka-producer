package com.example.kafkaproducer.service.impl;

import com.example.kafkaproducer.data.WordSendingEvent;
import com.example.kafkaproducer.kafkaP.KafkaProducerService;
import com.example.kafkaproducer.service.GenerateWordService;
import com.example.kafkaproducer.service.WordEventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class WordEventServiceImpl implements WordEventService {
    private KafkaProducerService kafkaProducerService;
    private GenerateWordService generateWordService;

    /**
     *  Prepare Data for Kafka and send to kafkaProducerService
     */
    @Override
    public String generateAndSend() {
        var word = generateWordService.generateWord();
        send(word);
        log.info("Word Sent - " + word);
        return word;
    }

    private void send(String word) {
        var event = new WordSendingEvent(word);
        log.debug("Event created {}", event);
        kafkaProducerService.send(event);
    }
}
