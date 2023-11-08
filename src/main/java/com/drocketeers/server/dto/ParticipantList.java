package com.drocketeers.server.dto;

import com.drocketeers.server.model.Participant;

import java.util.List;

public record ParticipantList(
        List<Participant> participants
) {
}
