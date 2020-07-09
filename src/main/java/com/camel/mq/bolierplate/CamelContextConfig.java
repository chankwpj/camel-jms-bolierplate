package com.camel.mq.bolierplate;

import lombok.RequiredArgsConstructor;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class CamelContextConfig {

    private final String JMS = "jms";
    private final ActiveMqProperties activeMqProperties;

    @Bean
    public ActiveMQConnectionFactory jmsConnectionFactory() {
        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
        cf.setBrokerURL(activeMqProperties.getUrl());
        cf.setUserName(activeMqProperties.getUsername());
        cf.setPassword(activeMqProperties.getPassword());
        return cf;
    }

    @Bean
    public PooledConnectionFactory pooledConnectionFactory(final ActiveMQConnectionFactory jmsConnectionFactory) {
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(jmsConnectionFactory);
//        pooledConnectionFactory.setMaxConnections(8);
//        pooledConnectionFactory.setMaximumActiveSessionPerConnection(5);
        return pooledConnectionFactory;
    }

    @Bean
    public JmsConfiguration jmsConfiguration(final PooledConnectionFactory pooledConnectionFactory) {
        JmsConfiguration jmsConfiguration = new JmsConfiguration();
        jmsConfiguration.setConnectionFactory(pooledConnectionFactory);
        jmsConfiguration.setTransacted(false);
//        jmsConfiguration.setConcurrentConsumers(10);
        return jmsConfiguration;
    }

    @Bean
    public ActiveMQComponent activeMQComponent(final JmsConfiguration jmsConfiguration) {
        final ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConfiguration(jmsConfiguration);
        return activeMQComponent;
    }

    @Bean
    public CamelContext camelContext(final ActiveMQComponent activeMQComponent,
                              final List<RouteBuilder> routeBuilders) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addComponent(JMS, activeMQComponent);
        camelContext.setAutoStartup(true);
        for (final RouteBuilder routeBuilder : routeBuilders) {
            camelContext.addRoutes(routeBuilder);
        }
        camelContext.start();
        return camelContext;
    }

    @Bean
    public JmsTemplate jmsProducer(final ActiveMQConnectionFactory jmsConnectionFactory) {
        return new JmsTemplate(jmsConnectionFactory);
    }


}
