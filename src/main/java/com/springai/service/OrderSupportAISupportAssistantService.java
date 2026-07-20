package com.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderSupportAISupportAssistantService {
    private final ChatClient chatClient;

    @Value("classpath:prompts/order_system_template.st")
    private Resource systemOrderPrompt;
    @Value("classpath:prompts/order_user_template.st")
    private Resource orderUserPrompt;

    @Value("classpath:prompts/order_system_policy_template.st")
    private Resource orderSystemPolicyPrompt;

    public OrderSupportAISupportAssistantService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String talkToAISupport(String customerName,String orderId, String customerMessage )
    {

        //String PromptUserSpec;
        return chatClient
                .prompt()
                .system(orderSystemPolicyPrompt)
                .user(promptUserSpec -> promptUserSpec
                        .text(orderUserPrompt)
                        .param("customerName", customerName)
                        .param("orderId", orderId)
                        .param("customerMessage", customerMessage))
                .call()
                .content();


    }

    public String assistWithOrderSupport(String customerName,String orderId, String customerMessage )
    {

        //String PromptUserSpec;
        return chatClient
                .prompt()
                .system(systemOrderPrompt)
                .user(promptUserSpec -> promptUserSpec
                        .text(orderUserPrompt)
                        .param("customerName", customerName)
                        .param("orderId", orderId)
                        .param("customerMessage", customerMessage))
                .call()
                .content();


    }
}

