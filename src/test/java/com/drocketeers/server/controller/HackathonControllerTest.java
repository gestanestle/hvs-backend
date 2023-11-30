package com.drocketeers.server.controller;

import com.drocketeers.server.ServerApplication;
import com.drocketeers.server.config.BaseContainerEnv;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(classes = ServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HackathonControllerTest extends BaseContainerEnv {

    @Test
    void getAllHackathons() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/api/v1/hackathons")
        .then()
                .statusCode(200)
                .body("hackathons", hasSize(2));
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