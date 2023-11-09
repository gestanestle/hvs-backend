package com.drocketeers.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "HACKATHON")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hackathonId;
    private String name;
    @Column(unique = true)
    private Integer season;
    private LocalDateTime registrationDeadline;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private String description;
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User createdBy;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> participants;

    public Hackathon(String name, Integer season, LocalDateTime registrationDeadline, LocalDateTime eventStart,
                     LocalDateTime eventEnd, String description, LocalDateTime createdAt, User createdBy, Set<User> participants) {
        this.name = name;
        this.season = season;
        this.registrationDeadline = registrationDeadline;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.description = description;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.participants = participants;
    }

    public static Hackathon create(String name, Integer season, LocalDateTime registrationDeadline, LocalDateTime eventStart,
                            LocalDateTime eventEnd, String description, User createdBy) {
        return new Hackathon(name, season, registrationDeadline, eventStart, eventEnd, description, LocalDateTime.now(), createdBy, new HashSet<>());
    }


    @Override
    public String toString() {
        return "Hackathon {" +
                "hackathonId=" + hackathonId +
                ", name='" + name + '\'' +
                ", season=" + season +
                ", registrationDeadline=" + registrationDeadline +
                ", eventStart=" + eventStart +
                ", eventEnd=" + eventEnd +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                ", participants=" + participants +
                " }";
    }
}
