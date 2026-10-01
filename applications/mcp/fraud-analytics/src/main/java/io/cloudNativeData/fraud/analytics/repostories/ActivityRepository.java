package io.cloudNativeData.fraud.analytics.repostories;

import io.cloudNativeData.fraud.analytics.repostories.entity.ActivityEntity;
import io.cloudNativeData.fraud.domains.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityEntity, String> {

    /**
     * Selects and returns all converted Activity payload objects directly.
     */
    @Query(value = "SELECT a.payload FROM fraud.activity_entity a", nativeQuery = true)
    List<Activity> findAllActivities();
}
