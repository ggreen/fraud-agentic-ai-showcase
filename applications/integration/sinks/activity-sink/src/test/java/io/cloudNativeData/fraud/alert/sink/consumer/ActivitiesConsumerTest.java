package io.cloudNativeData.fraud.alert.sink.consumer;

import io.cloudNativeData.fraud.alert.sink.repository.ActivityRepository;
import io.cloudNativeData.fraud.alert.sink.repository.entity.ActivityEntity;
import io.cloudNativeData.fraud.domains.Activity;
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivitiesConsumerTest {

    private final Activity alert = JavaBeanGeneratorCreator.of(Activity.class).create();

    @Mock
    private Converter<Activity, ActivityEntity> converter;
    private ActivitiesConsumer subject;
    @Mock
    private ActivityRepository repository;

    @Mock
    private ActivityEntity entity;

    @BeforeEach
    void setUp() {
        subject = new ActivitiesConsumer(repository,converter);
    }

    @Test
    void given_alerts_when_accept_then_saveAll() {

        when(converter.convert(any())).thenReturn(entity);

        subject.accept(alert);

        verify(repository).save(any(ActivityEntity.class));
    }
}