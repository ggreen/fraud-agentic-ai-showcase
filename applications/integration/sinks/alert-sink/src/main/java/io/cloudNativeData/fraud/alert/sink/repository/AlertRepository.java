package io.cloudNativeData.fraud.alert.sink.repository;

import io.cloudNativeData.fraud.domains.Alert;
import org.springframework.data.gemfire.repository.GemfireRepository;

public interface AlertRepository extends GemfireRepository<Alert,String> {
}
