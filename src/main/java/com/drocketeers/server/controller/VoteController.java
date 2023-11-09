package com.drocketeers.server.controller;

import com.drocketeers.server.dto.VoteDTO;
import com.drocketeers.server.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Object> setVote(@RequestBody VoteDTO voteDto) {
        voteService.setVote(voteDto.hackathonId(), voteDto.userId(), voteDto.teamId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Boolean>> userHasVote(@RequestParam("hackathon") Long hackathonId,
                                                            @RequestParam("user") Long userId) {
        boolean hasVote = voteService.hasVote(hackathonId, userId);
        return new ResponseEntity<>(Map.of("hasVote", hasVote), HttpStatus.OK);
    }
}
