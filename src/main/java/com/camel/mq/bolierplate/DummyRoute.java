package com.camel.mq.bolierplate;

import lombok.AllArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DummyRoute extends RouteBuilder {

    private final DummyQueueConsumer consumer;

    @Override
    public void configure() {
        onException(RetryableException.class)
                .redeliveryDelay(2000)
                .maximumRedeliveries(2)
                .logRetryStackTrace(true)
                .end();

        from("activemq:queue:dummy.queue")
                .bean(consumer, "consume")
                .errorHandler(deadLetterChannel("activemq:queue:dummy.queue.dlq"))
                .end();
    }

}
