package com.arnoldgalovics.blog.webclientresilience4j;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

@Configuration
public class ExternalApiConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:9090");
    }

    @Bean
    public CircuitBreakerConfigCustomizer externalServiceFooCircuitBreakerConfig() {
        return CircuitBreakerConfigCustomizer
                .of("externalServiceFoo",
                        builder -> builder.slidingWindowSize(10)
                                .slidingWindowType(COUNT_BASED)
                                .waitDurationInOpenState(Duration.ofSeconds(5))
                                .minimumNumberOfCalls(5)
                                .failureRateThreshold(50.0f));
    }
}
