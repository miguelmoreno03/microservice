package com.dh.catalogservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
public class AppCircuitBreakerConfig {
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer(){
        return resilience4JCircuitBreakerFactory -> resilience4JCircuitBreakerFactory.configureDefault(s -> new Resilience4JConfigBuilder(s)
                .circuitBreakerConfig(CircuitBreakerConfig.custom().slidingWindowSize(10)
                        .failureRateThreshold(50).waitDurationInOpenState(Duration.ofSeconds(10L))
                        .slowCallRateThreshold(50).slowCallDurationThreshold(Duration.ofSeconds(4L))
                        .build())
                .timeLimiterConfig(TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofSeconds(6L))
                        .build())
                .build()
        );

    }
}
