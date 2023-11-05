package com.drocketeers.server.dto.user;

import com.drocketeers.server.dto.user.payload.UserData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Event(
        UserData data,
        String object,
        String type
) {
}

