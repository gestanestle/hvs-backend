package com.drocketeers.server.model;

import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teamId;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> members;
    private Integer votes;
    @Transient
    private Float votePercentage;
    private LocalDateTime createdAt;

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

    public Team() {
    }

    public Team(Long teamId, String name, Hackathon hackathon, Set<User> members, Integer votes, Float votePercentage, LocalDateTime createdAt) {
        this.teamId = teamId;
        this.name = name;
        this.hackathon = hackathon;
        this.members = members;
        this.votes = votes;
        this.votePercentage = votePercentage;
        this.createdAt = createdAt;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Float getVotePercentage() {
        return votePercentage;
    }

    public void setVotePercentage(Float votePercentage) {
        this.votePercentage = votePercentage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
