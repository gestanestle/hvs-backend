package com.drocketeers.server.service;

import com.drocketeers.server.exception.ApiException;
import com.drocketeers.server.model.Hackathon;
import com.drocketeers.server.model.Team;
import com.drocketeers.server.model.User;
import com.drocketeers.server.model.Vote;
import com.drocketeers.server.repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

public interface VoteService {
    void setVote(Long hackathonId, Long userId, Long teamId);
    Long getVote(Long hackathonId, Long userId);

}

@Service
@Transactional
class VoteServiceImpl implements VoteService {

    private static final Logger log = LoggerFactory.getLogger(VoteServiceImpl.class);

    private final HackathonService hackathonService;
    private final UserService userService;
    private final TeamService teamService;
    private final VoteRepository voteRepository;

    public VoteServiceImpl(HackathonService hackathonService, UserService userService, TeamService teamService, VoteRepository voteRepository) {
        this.hackathonService = hackathonService;
        this.userService = userService;
        this.teamService = teamService;
        this.voteRepository = voteRepository;
    }

    @Override
    public void setVote(Long hackathonId, Long userId, Long teamId) {

        log.info("User ID: " + userId);
        log.info("Team ID: " + teamId);

        Hackathon hackathon = hackathonService.getHackathonById(hackathonId);
        User user = userService.getUserById(userId);
        Team team = teamService.getTeamById(teamId);

        if (team.getMembers().contains(user)) throw new ApiException(
                HttpStatus.BAD_REQUEST, "Members cannot vote for their own team.");

        if (voteRepository.getVoteByHackathonAndUser(hackathonId, userId).isPresent()) throw new ApiException(
                HttpStatus.BAD_REQUEST, "Users can only vote once per hackathon event."
        );

        voteRepository.save(new Vote(hackathon, user, team));  // save vote itself
        teamService.incrementTeamVotes(teamId);     // increment vote counter in TEAM
    }

    @Override
    public Long getVote(Long hackathonId, Long userId) {
        log.info("Voted at: " + hackathonId);
        log.info("Voted by: " + userId);

        boolean hasVote = voteRepository.getVoteByHackathonAndUser(hackathonId, userId).isPresent();
        log.info("User has vote? " + hasVote);

        if (!hasVote) throw new ApiException(HttpStatus.BAD_REQUEST, "User has not voted yet.");
        Vote vote = voteRepository.getVoteByHackathonAndUser(hackathonId, userId).orElseThrow();
        log.info("Voted for: " + vote);

        return vote.getVotedFor().getTeamId();
    }
}
