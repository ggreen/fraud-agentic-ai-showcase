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
public class AlertsConsumer implements Consumer<Alert> {

    private final AlertRepository repository;

    @Override
    public void accept(Alert alert) {
      log.info("Received alert:{} ", alert);
        repository.save(alert);
    }
}
