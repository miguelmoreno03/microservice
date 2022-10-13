package com.dh.serieservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQSenderConfig {
    @Value("${queue.series.name}")
    private String serieQueue;

    @Bean
    public Queue getSubjectQueue(){
        return new Queue(this.serieQueue,true);
    }
}
