package io.cloudNativeData.fraud.analytics.mcp.tools;

import io.cloudNativeData.fraud.analytics.repostories.ActivityRepository;
import io.cloudNativeData.fraud.domains.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityToolsTest {


    private ActivityTools subject;

    @Mock
    private Activity activity;
    @Mock
    private ActivityRepository repository;


    @BeforeEach
    void setUp() {
        subject = new ActivityTools(repository);
    }

    @Test
    void getActivities() {

        List<Activity> expected =  List.of(activity);
        when(repository.findAllActivities()).thenReturn(expected);

        var actual = subject.activities();
        assertThat(actual).isEqualTo(expected);
    }
}