package showcase.alarm.ai.source;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitConfig {


    @Value("${stream.activity.filter.value}")
    private String filterValue;

    @Value("${stream.activity.filter.name:account}")
    private String filterPropName;


}
