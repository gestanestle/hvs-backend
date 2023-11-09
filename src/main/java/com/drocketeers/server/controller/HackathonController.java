package com.drocketeers.server.controller;

import com.drocketeers.server.dto.Hackathons;
import com.drocketeers.server.dto.Participant;
import com.drocketeers.server.dto.TeamDTO;
import com.drocketeers.server.dto.TeamList;
import com.drocketeers.server.model.Hackathon;
import com.drocketeers.server.service.HackathonService;
import com.drocketeers.server.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hackathons")
@RequiredArgsConstructor
public class HackathonController {

    private final HackathonService hackathonService;
    private final TeamService teamService;

    @GetMapping
    ResponseEntity<Hackathons> getAllHackathons() {
        return new ResponseEntity<>(hackathonService.getAllHackathons(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    ResponseEntity<Hackathon> getHackathon(@PathVariable("id") Long id) {
        return new ResponseEntity<>(hackathonService.getHackathonById(id), HttpStatus.OK);
    }

    @PostMapping(path = "{id}/participants")
    ResponseEntity<Object> registerToHackathon(@PathVariable("id") Long id, @RequestBody Participant participant) {
        hackathonService.addParticipant(id, participant.userId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "{id}/teams")
    public ResponseEntity<TeamList> getAllTeams(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new TeamList(teamService.getAllTeams(id)), HttpStatus.OK);
    }

    @GetMapping(path = "{id}/teams/{teamId}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable("id") Long id, @PathVariable("teamId") Long teamId) {
        return new ResponseEntity<>(teamService.getTeamByIdAndHackathon(id, teamId), HttpStatus.OK);
    }


}
