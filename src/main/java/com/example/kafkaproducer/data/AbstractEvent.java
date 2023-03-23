package com.example.kafkaproducer.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Abstract Event For All Messages To Kafka
 * In this project - only  One message - WordSendingEvent.class
 */
public abstract class AbstractEvent {
    @JsonIgnore
    public abstract String getTopicName();

    @JsonIgnore
    public abstract String getEventId();
}
