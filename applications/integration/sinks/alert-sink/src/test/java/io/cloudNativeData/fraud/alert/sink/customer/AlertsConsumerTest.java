package io.cloudNativeData.fraud.alert.sink.customer;

import io.cloudNativeData.fraud.alert.sink.repository.AlertRepository;
import io.cloudNativeData.fraud.domains.Alert;
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.apache.geode.cache.snapshot.SnapshotFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AlertsConsumerTest {

    private List<Alert> alerts = new ArrayList<>(JavaBeanGeneratorCreator.of(Alert.class)
            .createCollection(10));
    private AlertsConsumer subject;
    @Mock
    private AlertRepository repository;

    @BeforeEach
    void setUp() {
        subject = new AlertsConsumer(repository);
    }

    @Test
    void given_alerts_when_accept_then_saveAll() {

        subject.accept(alerts);

        verify(repository).saveAll(any());
    }
}