package com.drocketeers.server.model;

import com.drocketeers.server.dto.user.payload.EmailAddressPayload;
import com.drocketeers.server.dto.user.payload.UserData;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class UserFactory {

    private static final String authId = "watermelon";
    private static final String username = "sugar";
    private static final Long createdAt = 1654014318265L;
    private static final String emailAd = "watermelon@gmail.com";

    public static User createUser() {
        return  new User(authId, username,
                emailAd, null, null, null, null,
                LocalDateTime.ofInstant(Instant.ofEpochMilli(createdAt), ZoneId.systemDefault()));
    }

    public static User createUser(String username) {
        return  new User(authId, username,
                emailAd, null, null, null, null,
                LocalDateTime.ofInstant(Instant.ofEpochMilli(createdAt), ZoneId.systemDefault()));
    }

    public static UserData createUserData() {
        return new UserData(authId, username, null, null, null, String.valueOf(createdAt),
                null, List.of(new EmailAddressPayload("emailId", emailAd)), "emailId", null);
    }
}
