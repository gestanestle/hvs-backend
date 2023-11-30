package com.drocketeers.server.service;

import com.drocketeers.server.dto.TeamDTO;
import com.drocketeers.server.model.Team;
import com.drocketeers.server.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface TeamService {

    List<TeamDTO> getAllTeams(Long hackathonId);
    Team getTeamById(Long teamId);
    TeamDTO getTeamByIdAndHackathon(Long hackathonId, Long teamId);
    void incrementTeamVotes(Long teamId);

}

@Service
@Transactional
class TeamServiceImpl implements TeamService {

    private static final Logger log = LoggerFactory.getLogger(TeamServiceImpl.class);

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<TeamDTO> getAllTeams(Long hackathonId) {

        return teamRepository.getTeamsByHackathon(hackathonId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow();
    }

    @Override
    public TeamDTO getTeamByIdAndHackathon(Long hackathonId, Long teamId) {
        Team team = teamRepository.getTeamByHackathonAndTeamId(hackathonId, teamId).orElseThrow();
        return mapToDTO(team);
    }

    @Override
    public void incrementTeamVotes(Long teamId) {
        teamRepository.incrementVotes(teamId);
    }

    private TeamDTO mapToDTO(Team team) {
        int sum = teamRepository.getSumOfVotes(team.getHackathon().getHackathonId());
        if (sum > 0) {
            float quotient = (float) team.getVotes() / (float) sum;
            team.setVotePercentage((float) (quotient * 100.0));
        }
        log.info("Team votes: " + team.getVotes());
        log.info("Sum of votes: " + sum);
        log.info("Vote %: " + team.getVotePercentage());
        return new TeamDTO(team.getTeamId(), team.getName(), team.getHackathon().getHackathonId(),
                team.getMembers(), team.getVotes(), team.getVotePercentage(), team.getCreatedAt());
    }
}
