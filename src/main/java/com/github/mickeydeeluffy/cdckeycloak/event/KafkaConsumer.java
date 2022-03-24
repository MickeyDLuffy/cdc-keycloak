package com.github.mickeydeeluffy.cdckeycloak.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mickeydeeluffy.dto.DebeziumDto;
import com.github.mickeydeeluffy.dto.KeycloakUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.reflect.Type;


@RequiredArgsConstructor
@EnableKafka
@Configuration
@Slf4j
public class KafkaConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(groupId = "${variables.kafka.group_id}", topics = "${variables.kafka.topics}")
    void onNewUserPublished(String data) {
        try {
            ParameterizedTypeReference<DebeziumDto<KeycloakUserDto>> typeRef
                    = new ParameterizedTypeReference<>() {};

            TypeReference<DebeziumDto<KeycloakUserDto>> tr = new TypeReference<DebeziumDto<KeycloakUserDto>>(){
                public Type getType() {
                    return typeRef.getType();
                }
            };
        var payload = objectMapper.readValue(data, tr);
            log.info("payload {}", payload.payload());

        } catch (JsonProcessingException e) {
            log.error("ss {}",e);
            log.info("Error occured while parsing json");
        }
    }
}
