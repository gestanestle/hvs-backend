package com.drocketeers.server.service;

import com.drocketeers.server.exception.ApiException;
import com.drocketeers.server.model.Team;
import com.drocketeers.server.model.User;
import com.drocketeers.server.model.Vote;
import com.drocketeers.server.repository.VoteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


public interface VoteService {
    void setVote(Long userId, Long teamId);
    boolean hasVote(Long userId);

}

@Service
@RequiredArgsConstructor
@Transactional
class VoteServiceImpl implements VoteService {

    private final UserService userService;
    private final TeamService teamService;
    private final VoteRepository voteRepository;

    @Override
    public void setVote(Long userId, Long teamId) {
        User user = userService.getUserById(userId);
        Team team = teamService.getTeamById(teamId);
        if (team.members.contains(user)) throw new ApiException(
                HttpStatus.BAD_REQUEST, "Members cannot vote for their own team.");
        if (voteRepository.getVoteByUser(user).isPresent()) throw new ApiException(
                HttpStatus.BAD_REQUEST, "Users can only vote once."
        );
        voteRepository.save(new Vote(user, team));  // save vote itself
        teamService.incrementTeamVotes(teamId);     // increment vote counter in TEAM
    }

    @Override
    public boolean hasVote(Long userId) {
        User user = userService.getUserById(userId);
        return voteRepository.getVoteByUser(user).isPresent();
    }
}
