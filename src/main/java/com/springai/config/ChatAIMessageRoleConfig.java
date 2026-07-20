package com.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatAIMessageRoleConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder){
        return  chatClientBuilder.defaultSystem("""
                You are an insurance assistant.
                You shouldn't reveal internal policy details,
                 or internal reasoning.
                Respond ONLY with a short, customer-safe message.""").build();


    }
}
