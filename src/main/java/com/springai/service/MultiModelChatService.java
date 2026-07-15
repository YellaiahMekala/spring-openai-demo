package com.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MultiModelChatService {

    private final ChatClient openAIChatClient;
    private final ChatClient ollamaChatClient;

    public MultiModelChatService(@Qualifier("openAIChatClient")ChatClient openAIChatClient,@Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.openAIChatClient = openAIChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }


    public  String chatWithOpenAI(String message){
        return openAIChatClient
                .prompt(message)
                .call()
                .content();
    }

    public String chatWithOllama(String message){
        return ollamaChatClient
                .prompt(message)
                .call()
                .content();

    }

}
