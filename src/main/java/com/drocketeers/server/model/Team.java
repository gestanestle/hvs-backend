package com.drocketeers.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TEAM")
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long teamId;
    public String name;
    @OneToMany(fetch = FetchType.EAGER)
    public Set<Participant> members;
    public Integer votes;
    public LocalDateTime createdAt;

    public Team(String name, Set<Participant> members, Integer votes, LocalDateTime createdAt) {
        this.name = name;
        this.members = members;
        this.votes = votes;
        this.createdAt = createdAt;
    }

    public static Team create(String name, Set<Participant> members) {
        return new Team(name, members, 0, LocalDateTime.now());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Team team)) return false;
        return Objects.equals(teamId, team.teamId) && Objects.equals(name, team.name) && Objects.equals(members, team.members) && Objects.equals(votes, team.votes) && Objects.equals(createdAt, team.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, name, members, votes, createdAt);
    }

    @Override
    public String toString() {
        return "Team { " +
                "teamId: " + teamId +
                ", name: '" + name + '\'' +
                ", members: " + members +
                ", votes: " + votes +
                ", createdAt: " + createdAt +
                " }";
    }
}
