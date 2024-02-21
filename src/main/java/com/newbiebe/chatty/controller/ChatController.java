package com.newbiebe.chatty.controller;

import com.newbiebe.chatty.entity.OutputMessage;
import com.newbiebe.chatty.entity.WebMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController {
    private final WebClient webClient;

    @Autowired
    public ChatController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/").build();
    }
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Mono<OutputMessage> send(WebMessage message) throws Exception {
        String url = "/api/messages";
        return this.webClient.post()
                .uri(url)
                .body(Mono.just(message), WebMessage.class)
                .retrieve()
                .bodyToMono(OutputMessage.class);

    }
}

