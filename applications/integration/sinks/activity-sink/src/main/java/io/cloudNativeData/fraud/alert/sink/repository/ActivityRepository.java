package io.cloudNativeData.fraud.alert.sink.repository;

import io.cloudNativeData.fraud.alert.sink.repository.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<ActivityEntity,String> {
}
