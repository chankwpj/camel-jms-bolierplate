package com.camel.mq.bolierplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DummyQueueConsumer {

    public void consume(final String textMessage) {
        log.info("Received message: {}", textMessage);
    }

}
