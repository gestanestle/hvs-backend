package com.drocketeers.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "USER_ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String authId;
    private String username;
    private String email;
    private String birthdate;
    private String lastName;
    private String firstName;
    private String profileImageURL;
    private LocalDateTime createdAt;

    public User(String authId, String username, String email, String birthdate, String lastName, String firstName, String profileImageURL, LocalDateTime createdAt) {
        this.authId = authId;
        this.username = username;
        this.email = email;
        this.birthdate = birthdate;
        this.lastName = lastName;
        this.firstName = firstName;
        this.profileImageURL = profileImageURL;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;
        return Objects.equals(userId, user.userId) && Objects.equals(authId, user.authId) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(birthdate, user.birthdate) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(profileImageURL, user.profileImageURL) && Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, authId, username, email, birthdate, lastName, firstName, profileImageURL, createdAt);
    }


}
