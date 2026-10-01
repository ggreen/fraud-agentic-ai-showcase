package io.cloudNativeData.fraud.analytics.repostories.entity;

import io.cloudNativeData.fraud.domains.Activity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "activity_entity", schema = "fraud")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityEntity {
    @Id
    @Column(name = "id")
    private String id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payload", columnDefinition = "jsonb")
    private Activity payload;
}
