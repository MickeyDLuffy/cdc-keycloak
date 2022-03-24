package com.github.mickeydeeluffy.cdckeycloak.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Accessors(fluent = true)
public class DebeziumDto<T> {
    @JsonIgnoreProperties
    private Map<String, Object> schema;
    private Payload<T> payload;

    public static class Payload<T> {
        private T before;
        private T after;
        private Map<String, Object> source;
        private char op;
        @JsonProperty("ts_ms")
        private Instant tsMs;
    }
}
