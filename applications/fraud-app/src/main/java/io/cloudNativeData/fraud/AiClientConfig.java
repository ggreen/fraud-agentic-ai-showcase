package io.cloudNativeData.fraud;

import io.cloudNativeData.fraud.ai.tools.tools.AlertTools;
import io.cloudNativeData.fraud.repositories.AlertRepository;
import io.cloudNativeData.fraud.services.AiAnswerService;
import io.cloudNativeData.fraud.services.SimilaritiesService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.ToolCallingAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AiClientConfig {

    @Autowired
    SyncMcpToolCallbackProvider mcpTools;

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder, ToolCallingManager toolCallingManager)
    {
        var toolCallingAdvisor = ToolCallingAdvisor.builder()
                .toolCallingManager(toolCallingManager)
                .build();

        return chatClientBuilder
                .defaultTools(mcpTools)
                .defaultAdvisors(toolCallingAdvisor)
                .build();
    }

    @Bean
    QuestionAnswerAdvisor advisor(VectorStore vectorStore){

        SearchRequest searchRequest = SearchRequest
                .builder()
                .similarityThreshold(.75)
                .build();

        return QuestionAnswerAdvisor
                .builder(vectorStore)
                .searchRequest(searchRequest).build();
    }

    @Bean
    SimilaritiesService similaritiesService(VectorStore vectorStore)
    {
        return vectorStore::similaritySearch;

    }





    @Bean
    AiAnswerService answerService(ChatClient chatClient, List<Advisor> advisors, AlertRepository alertRepository)
    {
        return prompt -> {

            return chatClient
                    .prompt()
                    .user(prompt)
                    .tools(new AlertTools(alertRepository))

                    .advisors(advisors) //use GemFire vectorDB
                    .call().content();
            };
    }
}
