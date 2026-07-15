package com.springai.controller;

import com.springai.service.MessageRolesService;
import com.springai.service.OpenAIChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("openai/api")
public class OpenAIChatController {
    private OpenAIChatService openAIChatService;
    private  final MessageRolesService messageRolesService;

    public OpenAIChatController(OpenAIChatService openAIChatService, MessageRolesService messageRolesService) {
        this.openAIChatService = openAIChatService;
        this.messageRolesService = messageRolesService;
    }

    @GetMapping("/chat")
    public String chat(String message){
        return openAIChatService.chatWithOpenAI(message);
    }

    public String checkInsurancePolicy(@RequestParam String message){
        return  messageRolesService.checkPolicy(message);
    }


}
