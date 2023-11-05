package com.drocketeers.server.controller;

import com.drocketeers.server.model.User;
import com.drocketeers.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/participants")
    public ResponseEntity<List<User>> getAllParticipants() {
        return new ResponseEntity<>(userService.getAllParticipants(), HttpStatus.OK);
    }
}
