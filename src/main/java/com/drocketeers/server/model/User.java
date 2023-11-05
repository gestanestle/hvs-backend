package com.drocketeers.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "USER_ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(unique = true)
    private String username;
    private Boolean isParticipant;

    public User(String username, Boolean isParticipant) {
        this.username = username;
        this.isParticipant = isParticipant;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(isParticipant, user.isParticipant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, isParticipant);
    }

    @Override
    public String toString() {
        return "User {" +
                "userId: " + userId +
                ", username: '" + username + '\'' +
                ", isParticipant: " + isParticipant +
                '}';
    }
}
