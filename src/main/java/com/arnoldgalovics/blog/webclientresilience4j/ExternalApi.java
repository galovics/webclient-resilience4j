package com.arnoldgalovics.blog.webclientresilience4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ExternalApi {
    private final WebClient webClient;

    @CircuitBreaker(name = "externalServiceFoo")
    public Mono<String> callExternalApiFoo() {
        return webClient.get().uri("/external-foo").retrieve().bodyToMono(String.class);
    }
}