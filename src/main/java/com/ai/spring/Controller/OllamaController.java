package com.ai.spring.Controller;

import groovy.util.logging.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

@RestController

public class OllamaController {
    @Autowired
    private OllamaChatModel ollamaClient;

    @GetMapping(value = "/ollama/ai/chat", produces = "text/html; charset=utf-8")
    public Flux<String> chat(String prompt) {
        return ollamaClient.stream(prompt);
    }

    // 多模态
    @GetMapping("/ollama/ai/multimodal")
    public String multimodal() throws IOException {
        byte[] imageData = new ClassPathResource("/test.png").getContentAsByteArray();

        var userMessage = new UserMessage("Explain what do you see on this picture?",
                List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageData)));

        ChatResponse response = ollamaClient.call(
                new Prompt(List.of(userMessage), OllamaOptions.create().withModel("llava")));

        String content = response.getResult().getOutput().getContent();
        System.out.println(content);
        return content;
    }

}
