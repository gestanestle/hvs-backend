package com.drocketeers.server.repository;

import com.drocketeers.server.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Modifying
    @Query("UPDATE Team t SET t.votes = t.votes + 1 WHERE t.teamId = ?1")
    void incrementVotes(Long id);
}
