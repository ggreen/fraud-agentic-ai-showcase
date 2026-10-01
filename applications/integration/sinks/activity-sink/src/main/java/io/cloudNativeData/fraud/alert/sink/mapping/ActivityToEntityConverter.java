package io.cloudNativeData.fraud.alert.sink.mapping;

import io.cloudNativeData.fraud.alert.sink.repository.entity.ActivityEntity;
import io.cloudNativeData.fraud.domains.Activity;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ActivityToEntityConverter implements Converter<Activity, ActivityEntity> {

    @Override
    public ActivityEntity convert(@NonNull Activity activity) {
        log.info("ActivityToEntityConverter convert: {}", activity);

        return ActivityEntity.builder().activity(activity)
                .id(activity.id()).build();
    }
}
