package com.drocketeers.server.controller;

import com.drocketeers.server.dto.TeamList;
import com.drocketeers.server.model.Team;
import com.drocketeers.server.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<TeamList> getAllTeams() {
        return new ResponseEntity<>(new TeamList(teamService.getAllTeams()), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(teamService.getTeamById(id), HttpStatus.OK);
    }
}
