package com.drocketeers.server.model;

import com.drocketeers.server.dto.TeamDTO;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

public class TeamFactory {

    static final User user = UserFactory.createUser();
    static final Hackathon hackathon = HackathonFactory.createHackathon();

    static final String name = "D'rocketeers";
    public static Team createTeam() {
        return Team.create(name, hackathon, Set.of(user));
    }

    public static Team createTeam(User user) { return Team.create(name, hackathon, Set.of(user)); }
    public static TeamDTO getTeamDTO() {
        return new TeamDTO(1L, name, hackathon.getHackathonId(),
                Set.of(user), 0, null, hackathon.getCreatedAt());
    }
}
