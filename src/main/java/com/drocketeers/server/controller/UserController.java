package com.drocketeers.server.controller;

import com.drocketeers.server.dto.user.Event;
import com.drocketeers.server.model.User;
import com.drocketeers.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<Object> webhooksUser(@RequestBody Event event) {
        switch (event.type()) {
            case "user.created" -> userService.createUser(event.data());
            case "user.updated" -> log.info("Received user.updated event.");
            case "user.deleted" -> log.info("Received user.deleted event.");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "{username}")
    ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
