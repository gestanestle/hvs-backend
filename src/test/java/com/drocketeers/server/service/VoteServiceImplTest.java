package com.drocketeers.server.service;

import com.drocketeers.server.exception.ApiException;
import com.drocketeers.server.model.*;
import com.drocketeers.server.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceImplTest {

    @Mock private HackathonService hackathonService;
    @Mock private UserService userService;
    @Mock private TeamService teamService;
    @Mock private VoteRepository voteRepository;

    @Autowired @InjectMocks
    private VoteServiceImpl voteService;

    @Captor
    ArgumentCaptor<Vote> captor;

    Hackathon hackathon;
    User teamMemberUser;
    User voter;
    Team team;
    Vote vote;

    @BeforeEach
    void setUp() {
        voteService = new VoteServiceImpl(hackathonService, userService, teamService, voteRepository);
        hackathon = HackathonFactory.createHackathon();
        teamMemberUser = UserFactory.createUser("user_team_member");
        voter = UserFactory.createUser("user_voter");
        team = TeamFactory.createTeam(teamMemberUser);
        vote = new Vote(hackathon, voter, team);
    }

    @Test
    void setVoteSuccessFul() {

        when(hackathonService.getHackathonById(anyLong())).thenReturn(hackathon);
        when(userService.getUserById(anyLong())).thenReturn(voter);
        when(teamService.getTeamById(anyLong())).thenReturn(team);

        voteService.setVote(1L, 1L, 1L);
        verify(voteRepository).save(captor.capture());
        assertThat(captor.getValue()).isEqualTo(vote);
    }

    @Test
    void setVoteButVoterIsTeamMember() {

        when(hackathonService.getHackathonById(anyLong())).thenReturn(hackathon);
        when(userService.getUserById(anyLong())).thenReturn(teamMemberUser);
        when(teamService.getTeamById(anyLong())).thenReturn(team);

        assertThatThrownBy((() -> voteService.setVote(1L, 1L, 1L)))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining("Members cannot vote for their own team.");
    }

    @Test
    void setVoteButHasVotedAlready() {

        when(hackathonService.getHackathonById(anyLong())).thenReturn(hackathon);
        when(userService.getUserById(anyLong())).thenReturn(voter);
        when(teamService.getTeamById(anyLong())).thenReturn(team);

        when(voteRepository.getVoteByHackathonAndUser(anyLong(), anyLong()))
                .thenReturn(Optional.of(vote));

        assertThatThrownBy(() -> voteService.setVote(1L, 1L, 1L))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining("Users can only vote once per hackathon event.");
    }

    @Test
    void getVote() {
        when(voteRepository.getVoteByHackathonAndUser(anyLong(), anyLong())).thenReturn(Optional.of(vote));
        assertThat(voteService.getVote(1L, 1L)).isEqualTo(vote.votedFor.teamId);
    }
}