package com.drocketeers.server.dto;

import com.drocketeers.server.model.Team;

import java.util.List;

public record TeamList(
        List<Team> teams
) {
}
