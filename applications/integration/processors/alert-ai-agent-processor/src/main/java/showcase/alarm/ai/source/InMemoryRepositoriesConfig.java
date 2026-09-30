package showcase.alarm.ai.source;

import io.cloudNativeData.fraud.domains.Activity;
import nyla.solutions.core.patterns.repository.memory.ListRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class InMemoryRepositoriesConfig {

    @Bean
    ListRepository<Activity> activitiesRepository()
    {
        return new ListRepository<>(new ArrayList<>());
    }
}
