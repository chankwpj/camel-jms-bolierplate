package com.camel.mq.bolierplate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("activemq.broker")
public class ActiveMqProperties {

    private String url;
    private String username;
    private String password;

}

