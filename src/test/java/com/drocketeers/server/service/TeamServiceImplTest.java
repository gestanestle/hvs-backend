package com.drocketeers.server.service;

import com.drocketeers.server.dto.TeamDTO;
import com.drocketeers.server.model.Team;
import com.drocketeers.server.model.TeamFactory;
import com.drocketeers.server.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    @Mock  private TeamRepository teamRepository;
    @InjectMocks @Autowired
    private TeamServiceImpl teamService;

    Team team = TeamFactory.createTeam();
    TeamDTO teamDTO = TeamFactory.getTeamDTO();

    @BeforeEach
    void setUp() {
        teamService = new TeamServiceImpl(teamRepository);
    }

    @Test
    void getAllTeams() {
        when(teamRepository.getTeamsByHackathon(anyLong())).thenReturn(List.of(team));
        assertThat(teamService.getAllTeams(1L))
                .usingRecursiveComparison()
                .ignoringFields("teamId", "createdAt")
                .isEqualTo(List.of(teamDTO));
        verify(teamRepository).getTeamsByHackathon(anyLong());
    }

    @Test
    void getTeamById() {
        when(teamRepository.findById(anyLong())).thenReturn(Optional.of(team));
        assertThat(teamService.getTeamById(1L)).isEqualTo(team);
        verify(teamRepository).findById(anyLong());
    }

    @Test
    void getTeamByIdAndHackathon() {
        when(teamRepository.getTeamByHackathonAndTeamId(anyLong(), anyLong())).thenReturn(Optional.of(team));
        assertThat(teamService.getTeamByIdAndHackathon(1L, 1L))
                .usingRecursiveComparison()
                .ignoringFields("teamId", "createdAt").isEqualTo(teamDTO);
        verify(teamRepository).getTeamByHackathonAndTeamId(anyLong(), anyLong());
    }

    @Test
    void incrementTeamVotes() {
        teamService.incrementTeamVotes(1L);
        verify(teamRepository).incrementVotes(anyLong());
    }
}