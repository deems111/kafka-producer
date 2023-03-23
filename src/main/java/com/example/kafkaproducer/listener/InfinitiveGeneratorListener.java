package com.example.kafkaproducer.listener;

import com.example.kafkaproducer.service.WordEventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Component For Unlimited Loop
 */
@Slf4j
@AllArgsConstructor
@Component
public class InfinitiveGeneratorListener implements ApplicationListener<ApplicationReadyEvent>  {
    private final WordEventService wordEventService;

    //Not limited by Time
    //TODO - test profile and prod profile
    //TODO - make async
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Starting generation words");
        int counter = 0;
        while (true) {
            counter++;
            var word = wordEventService.generateAndSend();
            log.info("Word generated: {} counter {}", word, counter);
        }
    }
}
