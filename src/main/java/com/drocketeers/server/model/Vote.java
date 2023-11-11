package com.drocketeers.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "VOTE")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
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
