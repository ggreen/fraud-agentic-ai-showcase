package io.cloudNativeData.fraud.ai.tools.tools;

import io.cloudNativeData.fraud.domains.Alert;
import io.cloudNativeData.fraud.repositories.AlertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;

@Slf4j
@RequiredArgsConstructor
public class AlertTools {
    private final AlertRepository alertRepository;

    @Tool(description = "Get list of current alerts")
    public Iterable<Alert> getAlerts() {
        log.info("Getting alerts in Tool");
        var results = alertRepository.findAll();

        log.info("Results: {}", results);
        return results;
    }
}
