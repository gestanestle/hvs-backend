package com.drocketeers.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "VOTE")
@AllArgsConstructor
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long voteId;
    @ManyToOne(fetch = FetchType.LAZY)
    public Hackathon hackathon;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User votedBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    public Team votedFor;

    public Vote(Hackathon hackathon, User votedBy, Team votedFor) {
        this.hackathon = hackathon;
        this.votedBy = votedBy;
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
                "voteId=" + voteId +
                ", hackathon=" + hackathon +
                ", votedBy=" + votedBy +
                ", votedFor=" + votedFor +
                " }";
    }
}
