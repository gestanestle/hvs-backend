package com.drocketeers.server.controller;

import com.drocketeers.server.ServerApplication;
import com.drocketeers.server.config.PostgresContainerEnv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = ServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(PostgresContainerEnv.class)
class HackathonControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllHackathons() {

    }
    @Test
    void getHackathon() {
    }

    @Test
    void registerToHackathon() {
    }

    @Test
    void getAllTeams() {
    }

    @Test
    void getTeamById() {
    }
}