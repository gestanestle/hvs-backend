package com.drocketeers.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PARTICIPANT")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User user;

    public Participant(User user) {
        this.user = user;
    }

}
