package com.drocketeers.server.service;

import com.drocketeers.server.model.Team;
import com.drocketeers.server.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();
    Team getTeamById(Long id);
    void incrementTeamVotes(Long id);

}

@Service
@RequiredArgsConstructor
@Transactional
class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow();
    }

    @Override
    public void incrementTeamVotes(Long id) {
        teamRepository.incrementVotes(id);
    }
}
