package com.github.mickeydeeluffy.cdckeycloak.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class KeycloakUserDto {
    private UUID id;
    private String username;
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private boolean enabled = false;
    @JsonProperty("created_timestamp")
    private Instant createdTimestamp;
    @JsonProperty("realm_id")
    private String realm;
    private String[] roles;
}
