package com.drocketeers.server.repository;

import com.drocketeers.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.isParticipant = true")
    List<User> getAllParticipants();

}
