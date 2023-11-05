package com.drocketeers.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
    @OneToMany(fetch = FetchType.EAGER)
    public Set<Participant> members;
    public Integer votes;

    public Team(Set<Participant> members, Integer votes) {
        this.members = members;
        this.votes = votes;
    }

    public static Team create(Set<Participant> members) {
        return new Team(members, 0);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Team team)) return false;
        return Objects.equals(teamId, team.teamId) && Objects.equals(members, team.members) && Objects.equals(votes, team.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, members, votes);
    }

    @Override
    public String toString() {
        return "Team {" +
                "teamId: " + teamId +
                ", members: " + members +
                ", votes: " + votes +
                '}';
    }
}
