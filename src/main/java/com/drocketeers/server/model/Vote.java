package com.drocketeers.server.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "VOTE")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long voteId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hackathon_Id")
    private Hackathon hackathon;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User votedBy;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team votedFor;

    public Vote(Hackathon hackathon, User votedBy, Team votedFor) {
        this.hackathon = hackathon;
        this.votedBy = votedBy;
        this.votedFor = votedFor;
    }

    public Vote() {
    }

    public Vote(Long voteId, Hackathon hackathon, User votedBy, Team votedFor) {
        this.voteId = voteId;
        this.hackathon = hackathon;
        this.votedBy = votedBy;
        this.votedFor = votedFor;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    public User getVotedBy() {
        return votedBy;
    }

    public void setVotedBy(User votedBy) {
        this.votedBy = votedBy;
    }

    public Team getVotedFor() {
        return votedFor;
    }

    public void setVotedFor(Team votedFor) {
        this.votedFor = votedFor;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Vote vote)) return false;
        return Objects.equals(voteId, vote.voteId) && Objects.equals(hackathon, vote.hackathon) && Objects.equals(votedBy, vote.votedBy) && Objects.equals(votedFor, vote.votedFor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteId, hackathon, votedBy, votedFor);
    }

    @Override
    public String toString() {
        return "Vote {" +
                "voteId= " + voteId +
                ", hackathon= " + hackathon +
                ", votedBy= " + votedBy +
                ", votedFor= " + votedFor +
                " }";
    }
}
