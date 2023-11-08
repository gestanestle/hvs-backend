package com.drocketeers.server.repository;

import com.drocketeers.server.model.Participant;
import com.drocketeers.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query("SELECT p FROM Participant p WHERE p.user= ?1")
    Participant getParticipant(User user);

}
