package com.drocketeers.server.repository;

import com.drocketeers.server.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t WHERE t.hackathon.hackathonId = ?1")
    List<Team> getTeamsByHackathon(Long hackathonId);

    @Query("SELECT t FROM Team t WHERE t.hackathon.hackathonId = ?1 AND t.teamId = ?2")
    Optional<Team> getTeamByHackathonAndTeamId(Long hackathonId, Long teamId);

    @Modifying
    @Query("UPDATE Team t SET t.votes = t.votes + 1 WHERE t.teamId = ?1")
    void incrementVotes(Long id);

    @Query("SELECT SUM(t.votes) FROM Team t WHERE t.hackathon.hackathonId = ?1")
    Integer getSumOfVotes(Long hackathonId);
}
