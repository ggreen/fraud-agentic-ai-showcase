package io.cloudNativeData.fraud.analytics;

import io.cloudNativeData.fraud.analytics.mcp.tools.ActivityTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AiConfig {

    @Bean
    public ToolCallbackProvider weatherTools(ActivityTools activityTools) {
        return MethodToolCallbackProvider
                .builder().toolObjects(activityTools).build();
    }
}
