package io.cloudNativeData.fraud.alert.sink.consumer;

import io.cloudNativeData.fraud.alert.sink.repository.ActivityRepository;
import io.cloudNativeData.fraud.alert.sink.repository.entity.ActivityEntity;
import io.cloudNativeData.fraud.domains.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActivitiesConsumer implements Consumer<Activity> {

    private final ActivityRepository repository;
    private final Converter<Activity, ActivityEntity> converter;

    @Override
    public void accept(Activity activity) {
      log.info("Received activity:{} ", activity);
        repository.save(converter.convert(activity));
    }
}
