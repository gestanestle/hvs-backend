package com.drocketeers.server.repository;

import com.drocketeers.server.model.Hackathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HackathonRepository extends JpaRepository<Hackathon, Long> {

    @Query("SELECT h FROM Hackathon h WHERE h.season = ?1")
    Optional<Hackathon> getBySeason(Integer season);
}
