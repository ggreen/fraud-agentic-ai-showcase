package showcase.alarm.ai.source.service.ai;


import io.cloudNativeData.fraud.domains.Activity;
import io.cloudNativeData.fraud.domains.Alert;

import java.util.List;

@FunctionalInterface
public interface AlertsModelInference {
    List<Alert> determineAlert(List<Activity> activities);
}
