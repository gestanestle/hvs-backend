package com.drocketeers.server.dto;

import com.drocketeers.server.model.Hackathon;

import java.util.List;

public record Hackathons(
        List<Hackathon> hackathons
) {
}
