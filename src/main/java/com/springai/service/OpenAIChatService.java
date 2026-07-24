package com.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class OpenAIChatService {

    private final ChatClient chatClient;


    public OpenAIChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String chatWithOpenAI(String message) {
        List<String> arList = new ArrayList<>();
        arList.add("}");
        arList.add("]");
        arList.add("/*");

        ChatOptions chatOptions = ChatOptions.builder()
                .model("gpt-4o-mini")
                .temperature(0.5)
                .maxTokens(200)
                .topK(50)
                .topP(0.5)
                .frequencyPenalty(0.7)
                .presencePenalty(0.7)
                .stopSequences(arList)
                .build();
        return chatClient
                .prompt(message)
                .options(chatOptions)
                .call()
                .content();
    }

    public Flux<String> askToAIStream(String message) {
        return chatClient
                .prompt(message)
                .stream()
                .content();
    }
}
