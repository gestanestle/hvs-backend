package com.drocketeers.server.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "USER_ACCOUNT")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String authId;
    @Column(unique = true)
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

    public User() {
    }

    public User(Long userId, String authId, String username, String email, String birthdate, String lastName, String firstName, String profileImageURL, LocalDateTime createdAt) {
        this.userId = userId;
        this.authId = authId;
        this.username = username;
        this.email = email;
        this.birthdate = birthdate;
        this.lastName = lastName;
        this.firstName = firstName;
        this.profileImageURL = profileImageURL;
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User { " +
                "userId: " + userId +
                ", authId: '" + authId + '\'' +
                ", username: '" + username + '\'' +
                ", email: '" + email + '\'' +
                ", birthdate: '" + birthdate + '\'' +
                ", lastName: '" + lastName + '\'' +
                ", firstName: '" + firstName + '\'' +
                ", profileImageURL: '" + profileImageURL + '\'' +
                ", createdAt: " + createdAt +
                " }";
    }
}
