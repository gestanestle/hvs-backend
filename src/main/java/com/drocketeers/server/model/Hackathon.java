package com.drocketeers.server.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "HACKATHON")
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

    public Hackathon() {
    }

    public Hackathon(Long hackathonId, String name, Integer season, LocalDateTime registrationDeadline, LocalDateTime eventStart, LocalDateTime eventEnd, String description, LocalDateTime createdAt, User createdBy, Set<User> participants) {
        this.hackathonId = hackathonId;
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

    public Long getHackathonId() {
        return hackathonId;
    }

    public void setHackathonId(Long hackathonId) {
        this.hackathonId = hackathonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public LocalDateTime getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(LocalDateTime registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public LocalDateTime getEventStart() {
        return eventStart;
    }

    public void setEventStart(LocalDateTime eventStart) {
        this.eventStart = eventStart;
    }

    public LocalDateTime getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(LocalDateTime eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
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
