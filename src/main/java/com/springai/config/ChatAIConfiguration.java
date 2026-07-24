package com.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatAIConfiguration {

    @Bean
    public ChatClient openAIChatClient(OpenAiChatModel openAiChatModel){
        return ChatClient.builder(openAiChatModel).build();
    }

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel){
        return  ChatClient.builder(ollamaChatModel).build();
    }
/*    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder){
        return  chatClientBuilder
                .defaultOptions(ChatOptions.builder())

                .build();*/





}
