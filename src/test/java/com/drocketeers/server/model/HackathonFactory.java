package com.drocketeers.server.model;

import java.time.LocalDateTime;
import java.time.Month;

public class HackathonFactory {

    public static Hackathon createHackathon() {
        return Hackathon.create("Hackathon Season 1", 1,
                LocalDateTime.of(2023, Month.OCTOBER, 31, 0, 0),
                LocalDateTime.of(2023, Month.NOVEMBER, 4, 0, 0),
                LocalDateTime.of(2023, Month.NOVEMBER, 12, 0, 0),
                "Description of season 1 of Daedalus Hackathon",
                null);
    }
}
