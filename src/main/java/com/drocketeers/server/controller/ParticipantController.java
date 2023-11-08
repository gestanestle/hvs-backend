package com.drocketeers.server.controller;

import com.drocketeers.server.dto.ParticipantList;
import com.drocketeers.server.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @GetMapping
    public ResponseEntity<ParticipantList> getAllParticipants() {
        return new ResponseEntity<>(new ParticipantList(participantService.getAllParticipants()), HttpStatus.OK);
    }

}
