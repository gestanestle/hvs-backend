package com.drocketeers.server.controller;

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
    public ResponseEntity<Object> setVote(@RequestParam("user") Long userId,
                                        @RequestParam("team") Long teamId) {
        voteService.setVote(userId, teamId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Boolean>> userHasVote(@RequestParam("user") Long userId) {
        boolean hasVote = voteService.hasVote(userId);
        return new ResponseEntity<>(Map.of("hasVote", hasVote), HttpStatus.OK);
    }
}
