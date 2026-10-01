package io.cloudNativeData.fraud.analytics.mcp.tools;

import io.cloudNativeData.fraud.analytics.repostories.ActivityRepository;
import io.cloudNativeData.fraud.domains.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityTools {

    private final ActivityRepository repository;

    @Tool(description = "Get list of activities")
    public List<Activity> activities(){

        log.info("get activities");
        return repository.findAllActivities();
    }
}
