package io.cloudNativeData.fraud.domains;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Alert(String id,
                    String account,
                    String level,
                    String time,
                    String event) {
}
