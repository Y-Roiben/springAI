package com.ai.spring.Controller;

import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class OpenAIImageController {
    @Autowired
    private OpenAiImageModel openaiImageModel;


    @GetMapping("/ai/image")
    public String image(@RequestParam(value = "prompt", defaultValue = "猴子开飞机")
                            String prompt){
        ImageResponse response = openaiImageModel.call(
                new ImagePrompt(prompt));
        Image output = response.getResult().getOutput();
        return output.getUrl();
    }

}
