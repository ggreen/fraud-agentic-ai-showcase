package io.cloudNativeData.fraud.alert.sink.customer;

import io.cloudNativeData.fraud.alert.sink.repository.AlertRepository;
import io.cloudNativeData.fraud.domains.Alert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlertsConsumer implements Consumer<List<Alert>> {

    private final AlertRepository repository;

    @Override
    public void accept(List<Alert> alerts) {
      log.info("Received {} alerts", alerts.size());
        repository.saveAll(alerts);
    }
}
