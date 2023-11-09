package com.drocketeers.server.dto;

import com.drocketeers.server.model.User;

import java.time.LocalDateTime;
import java.util.Set;

public record TeamDTO(
        Long teamId, String name, Long hackathonId,
        Set<User> members, Integer votes, Float votePercentage,
        LocalDateTime createdAt
) {
}
