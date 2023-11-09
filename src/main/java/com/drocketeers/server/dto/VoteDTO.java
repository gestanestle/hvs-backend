package com.drocketeers.server.dto;

public record VoteDTO(
        Long hackathonId,
        Long userId,
        Long teamId
) {
}
