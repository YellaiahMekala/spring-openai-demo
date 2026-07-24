package com.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageRolesService {

    private static final String CLAIM_DETAILS = """
            Claim Details:
            Policy: BASIC
            Max Coverage: 20000
            Claim Amount: 50000
            """;
    private final ChatClient chatClient;

    public MessageRolesService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

//    public MessageRolesService(ChatClient.Builder chatClientBuilder) {
//        this.chatClient = chatClientBuilder
//                .defaultSystem("""
//                You are an insurance assistant.
//                You shouldn't reveal internal policy details,
//                 or internal reasoning.
//                Respond ONLY with a short, customer-safe message.
//                """)
////                .defaultUser("How can i help yu.?")
//                .build();
//    }

    public String checkPolicy(String message) {
//overridden system message specific api methods
        SystemMessage systemMessage = new SystemMessage("""
                You are an insurance assistant.
                You shouldn't reveal internal policy details,
                 or internal reasoning.
                Respond ONLY with a short, customer-safe message.
                """);
        //prompt injection can be unsafe without specific message roles
        UserMessage userMessage = new UserMessage("""
                %S
                Customer says: %s
                """.formatted(CLAIM_DETAILS, message));

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        //prompt with no-arg prompt methods

        return chatClient.prompt(prompt).call().content();

    }

    public String checkInsurancePolicy(String message) {
        return chatClient.prompt().system("""
                        You are an IT support assistant, "should help with system support issues to users""")
//                .system("""
//                        You are an insurance assistant.
//                        You shouldn't reveal internal policy details,
//                         or internal reasoning.
//                        Respond ONLY with a short, customer-safe message.
//                        """)
                .user("""
                         %S
                        Customer says:
                         %s """.formatted(CLAIM_DETAILS, message)).call().content();
    }

    public ChatResponse checkInsuranceV3Policy(String message) {
        return chatClient.prompt()
                .user("""
                         %S
                        Customer says:
                         %s """.formatted(CLAIM_DETAILS, message))
                .call().chatResponse();
    }

    public String guideMe(String topic, String level,int points) {
        return chatClient.prompt()
                .system("you are tech stack assistant, give the best  answer")
                .user("explain kafka"+topic +"in "+level+"level with"+ +points)
                .call().content();

    }

}
