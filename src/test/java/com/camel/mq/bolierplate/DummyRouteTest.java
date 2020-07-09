package com.camel.mq.bolierplate;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;

@SpringBootTest
class DummyRouteTest {

    @MockBean
    DummyQueueConsumer dummyQueueConsumer;
    @Autowired
    JmsTemplate jmsTemplate;

    @Test
    void consumeMessage() {
        final String textMessage = "some-text";
        jmsTemplate.send("dummy.queue", session -> session.createTextMessage(textMessage));
        verify(dummyQueueConsumer, timeout(5000)).consume(textMessage);
    }

    @Test
    void sendToDlqWithoutRetry() {
        final String textMessage = "some-text";
        doThrow(new IllegalArgumentException("mock-illegal-argument-exception")).when(dummyQueueConsumer).consume(any());
        jmsTemplate.send("dummy.queue", session -> session.createTextMessage(textMessage));
        assertEventually(() -> {
            verify(dummyQueueConsumer).consume(textMessage);
        });
        assertThat(jmsTemplate.receiveAndConvert("dummy.queue.dlq").toString()).isEqualTo(textMessage);

    }

    @Test
    void sendToDlqWithRetry() {
        final String textMessage = "some-text";
        doThrow(new RetryableException()).when(dummyQueueConsumer).consume(any());
        jmsTemplate.send("dummy.queue", session -> session.createTextMessage(textMessage));
        assertEventually(() -> verify(dummyQueueConsumer, times(3)).consume(textMessage));
        assertThat(jmsTemplate.receiveAndConvert("dummy.queue.dlq").toString()).isEqualTo(textMessage);
    }

    private void assertEventually(final Runnable runnable) {
        await().timeout(15, TimeUnit.SECONDS).untilAsserted(() -> runnable.run());
    }
}