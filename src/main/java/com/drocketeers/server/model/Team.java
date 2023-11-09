package com.drocketeers.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "TEAM")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long teamId;
    public String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hackathon_id")
    public Hackathon hackathon;
    @ManyToMany(fetch = FetchType.EAGER)
    public Set<User> members;
    public Integer votes;
    @Transient
    public Float votePercentage;
    public LocalDateTime createdAt;

    public Team(String name, Hackathon hackathon, Set<User> members, Integer votes, LocalDateTime createdAt) {
        this.name = name;
        this.hackathon = hackathon;
        this.members = members;
        this.votes = votes;
        this.createdAt = createdAt;
    }

    public static Team create(String name, Hackathon hackathon, Set<User> members) {
        return new Team(name, hackathon, members, 0, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Team { " +
                "teamId=" + teamId +
                ", name='" + name + '\'' +
                ", hackathon=" + hackathon +
                ", members=" + members +
                ", votes=" + votes +
                ", votePercentage=" + votePercentage +
                ", createdAt=" + createdAt +
                " }";
    }
}
