package com.drocketeers.server.repository;

import com.drocketeers.server.model.User;
import com.drocketeers.server.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v WHERE v.votedBy = ?1")
    Optional<Vote> getVoteByUser(User User);

}
