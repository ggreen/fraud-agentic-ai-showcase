package io.cloudNativeData.fraud.domain;

import lombok.Builder;

@Builder
public record PromptContext(String promptText, String context) {
}
