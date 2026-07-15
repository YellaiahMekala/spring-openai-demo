package com.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageRolesService {

    private final ChatClient chatClient;

    public MessageRolesService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String checkPolicy(String message) {
        //prompt injection can be unsafe without spcific message roles
        UserMessage userMessage = new UserMessage("""
                Policy details: BASIC
                Policy: PREMIUM
                Max Coverage: 20000
                Claim Amount: 50000
                Customer says: %s
                """.formatted(message));

        Prompt prompt =new Prompt(List.of(userMessage));

        return chatClient.prompt(prompt)
                .call()
                .content();

    }


}
