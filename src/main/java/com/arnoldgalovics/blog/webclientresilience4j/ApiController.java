package com.arnoldgalovics.blog.webclientresilience4j;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final ExternalApi externalApi;

    @GetMapping("/foo")
    public Mono<String> foo() {
        return externalApi.callExternalApiFoo();
    }
}
