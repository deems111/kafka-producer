package com.example.kafkaproducer.service.impl;

import com.example.kafkaproducer.service.GenerateWordService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class GenerateWordServiceImpl implements GenerateWordService, InitializingBean {
    @Value("${generator.word.useLetters}:true")
    private boolean useLetters;

    @Value("${generator.word.useNumbers}:false")
    private boolean useNumbers;

    @Value("${generator.word.length:100}")
    private Integer length;

    @Value("${generator.word.delay.ms}:50")
    private Integer delayMs;

    /**
     * Generate Random String of with length = generator.word.length,
     * using parameters - useNumbers / useLetters
     */
    @Override
    public String generateWord() {
        delayGenerating();
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    /**
     * Simulating Words Generating delay
     */
    @SneakyThrows
    private void delayGenerating() {
        //TODO - create service to update delayMs manually (f.e. using REST API) without rebooting of the app
        log.debug("Start of Delay on Generating Word, time = " + new Date().getTime());
        Thread.sleep(delayMs);
        log.debug("End   of Delay on Generating Word, time = " + new Date().getTime());
    }

    @Override
    public void afterPropertiesSet() {
        log.info("Properties of word generation word length = {}, delay ms = {} use numbers = {}, use letters = {}",
                length, delayMs, useNumbers, useLetters);
    }
}
