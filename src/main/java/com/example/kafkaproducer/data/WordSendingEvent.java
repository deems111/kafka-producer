package com.example.kafkaproducer.data;

import com.example.kafkaproducer.utility.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Object of Event Message, For Sending To Kafka
 */
@AllArgsConstructor
@Setter
@Getter
public class WordSendingEvent extends AbstractEvent {

    private final String word;
    private final String eventId = UUID.randomUUID().toString();

    @Override
    public String getTopicName() {
        return Constants.WORD_SENDING_TOPIC_NAME;
    }

    @Override
    public String getEventId() {
        return eventId;
    }

    @Override
    public String toString() {
        return "StringSendingEvent{" +
                "word='" + word + '\'' +
                ", eventId='" + eventId + '\'' +
                '}';
    }
}
