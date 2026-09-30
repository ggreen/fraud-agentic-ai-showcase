package showcase.alarm.ai.source.service;


import io.cloudNativeData.fraud.domains.Activity;
import io.cloudNativeData.fraud.domains.Alert;

import java.util.List;

/**
 * The alert detector service
 * @author gregory green
 */
public interface AlertDetectorService {
    /**
     *
     * @param activity the current activity
     * @return the list of detected alerts
     */
    List<Alert> detectAlerts(Activity activity);

    List<Alert> checkForAlerts();
}
