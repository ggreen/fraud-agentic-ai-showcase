package io.cloudNativeData.fraud.controller;

import io.cloudNativeData.fraud.domain.PromptContext;
import io.cloudNativeData.fraud.services.AiAnswerService;
import io.cloudNativeData.fraud.services.SimilaritiesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyla.solutions.core.patterns.integration.Publisher;
import org.springframework.ai.document.Document;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * * Vector Search Controller for search and evicting caching search results
 * @author Gregory Green
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("vector/search")
@Slf4j
public class VectorSearchController {

    private final Publisher<PromptContext> publisher;
    private final SimilaritiesService similaritiesService;
    private final AiAnswerService aiAnswerService;

    @PostMapping
//    @Cacheable("SearchResults")
    public String answerPrompt(@RequestBody String prompt) {
        log.info("prompt: {}",prompt);
        var response = aiAnswerService.answer(prompt);
        log.info("response: {}",response);
        return response;
    }

    /**
     * Publish prompt context and evict cache for prompt
     * @param prompt the prompt
     * @param context the context
     */
    @PostMapping("prompt/context")
    public void publishPromptContext(@RequestParam String prompt, @RequestParam String context) {

        publisher.send(PromptContext.builder()
                .promptText(prompt)
                .context(context).build());
    }

    @DeleteMapping("prompt")
    @CacheEvict(value = "SearchResults", key = "#prompt")
    public void evictPrompt(@RequestParam String prompt) {
        log.info("Deleted: {} using Spring Cache @CacheEvict",prompt);
    }


    @PostMapping("similarities")
    public List<Document> findSimilarities(@RequestBody String question) {
        return similaritiesService.findSimilarities(question);
    }
}
