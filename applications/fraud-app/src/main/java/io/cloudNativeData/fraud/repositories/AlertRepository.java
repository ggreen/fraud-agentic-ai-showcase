package io.cloudNativeData.fraud.repositories;

import io.cloudNativeData.fraud.domains.Alert;
import org.springframework.data.gemfire.repository.GemfireRepository;

public interface AlertRepository extends GemfireRepository<Alert,String> {
}
