package com.springai.service;

import com.springai.advisor.AuditTokenUsageAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .advisors(List.of(new SimpleLoggerAdvisor(),
                       new SafeGuardAdvisor(List.of("password","otp","cvv")
                ,"security reasons-never asks sensitive info",1),
                                new AuditTokenUsageAdvisor()))

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

