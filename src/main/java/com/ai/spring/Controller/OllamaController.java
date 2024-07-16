package com.ai.spring.Controller;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class OllamaController {
    @Autowired
    private OllamaChatModel ollamaClient;

    @GetMapping("/ollama/ai/chat")
    public Flux<String> chat(String prompt) {
        return ollamaClient.stream(prompt);
    }
}
