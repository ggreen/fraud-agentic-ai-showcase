package showcase.alarm.ai.source;

import io.cloudNativeData.fraud.domains.Activity;
import lombok.extern.slf4j.Slf4j;
import nyla.solutions.core.patterns.repository.memory.ListRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class LoadRepositoryConsumerConfig {

    @Bean
    Consumer<Activity> loadRepositoryConsumer(ListRepository<Activity> listRepository)
    {
        return activity -> {
            log.info("Saving Activities: {}",activity);
            listRepository.save(activity);
        };
    }
}
