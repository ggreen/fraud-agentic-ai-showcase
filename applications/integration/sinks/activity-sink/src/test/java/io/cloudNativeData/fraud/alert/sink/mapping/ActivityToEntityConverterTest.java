package io.cloudNativeData.fraud.alert.sink.mapping;

import io.cloudNativeData.fraud.alert.sink.repository.entity.ActivityEntity;
import io.cloudNativeData.fraud.domains.Activity;
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ActivityToEntityConverterTest {

    private final Activity activity = JavaBeanGeneratorCreator.of(Activity.class).create();
    private final ActivityToEntityConverter subject = new ActivityToEntityConverter();

    @Test
    void convert() {
        ActivityEntity  expected = ActivityEntity.builder().activity(activity)
                .id(activity.id()).build();

        var actual = subject.convert(activity);

        assertThat(actual).isEqualTo(expected);
    }
}