package com.upwork.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WebClientConfigTest {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    void webClientBuilderBeanExists() {
        assertThat(webClientBuilder).isNotNull();
    }

    @Test
    void webClientCanBeBuilt() {
        WebClient client = webClientBuilder.build();
        assertThat(client).isNotNull();
    }
}
