package io.cloudNativeData.fraud;

import io.cloudNativeData.fraud.ai.tools.tools.AlertTools;
import io.cloudNativeData.fraud.repositories.AlertRepository;
import io.cloudNativeData.fraud.services.AiAnswerService;
import io.cloudNativeData.fraud.services.SimilaritiesService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.ToolCallingAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.redis.RedisChatMemoryRepository;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.RedisClient;

import java.time.Duration;
import java.util.List;

@Configuration
public class AiClientConfig {

    @Autowired
    SyncMcpToolCallbackProvider mcpTools;

    @Value("${app.ai.chat.memory.id:agent-demo}")
    private String conversationId;

    @Bean
    RedisChatMemoryRepository chatMemoryRepository()
    {
        var jedisClient = RedisClient.builder().hostAndPort("localhost", 6379).build();

        return RedisChatMemoryRepository.builder()
                .jedisClient(jedisClient)
                .indexName("my-chat-index")
                .keyPrefix("my-chat:")
                .timeToLive(Duration.ofHours(24))
                .build();
    }


    @Bean
    ChatMemory chatMemory(RedisChatMemoryRepository chatMemoryRepository){
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(10)
                .build();

    }

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder,
                          ToolCallingManager toolCallingManager,
                          ChatMemory chatMemory)
    {
        var toolCallingAdvisor = ToolCallingAdvisor.builder()
                .toolCallingManager(toolCallingManager)
                .build();

        return chatClientBuilder
                .defaultTools(mcpTools)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
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
                    .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId)) //Chat Memory
                    .advisors(advisors) //use GemFire vectorDB
                    .call().content();
            };
    }
}
