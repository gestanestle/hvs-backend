package com.drocketeers.server.dto.user.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserData(
        String id,
        String username,
        String first_name,
        String last_name,
        String birthday,
        String created_at,
        String updated_at,
        List<EmailAddressPayload> email_addresses,
        String primary_email_address_id,
        String image_url

) {
}
