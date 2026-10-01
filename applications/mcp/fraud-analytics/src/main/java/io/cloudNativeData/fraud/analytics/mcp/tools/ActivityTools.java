package io.cloudNativeData.fraud.analytics.mcp.tools;

import io.cloudNativeData.fraud.domains.Activity;
import lombok.extern.slf4j.Slf4j;
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ActivityTools {

    private final List<Activity> activities = new ArrayList<>(JavaBeanGeneratorCreator
            .of(Activity.class).createCollection(11));

    @Tool(description = "Get list of activities")
    public List<Activity> activities(){

        log.debug("get activities");
        return activities;
    }
}
