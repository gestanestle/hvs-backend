package com.drocketeers.server.service;

import com.drocketeers.server.model.Participant;
import com.drocketeers.server.model.User;
import com.drocketeers.server.repository.ParticipantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ParticipantService {
    List<Participant> getAllParticipants();
    Participant getParticipantByUserId(Long userId);
}

@Service
@Transactional
@RequiredArgsConstructor
class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final UserService userService;

    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Participant getParticipantByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return participantRepository.getParticipant(user).get();
    }
}
