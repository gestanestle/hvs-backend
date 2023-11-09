package com.drocketeers.server.service;

import com.drocketeers.server.dto.Hackathons;
import com.drocketeers.server.exception.ApiException;
import com.drocketeers.server.model.Hackathon;
import com.drocketeers.server.model.User;
import com.drocketeers.server.repository.HackathonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface HackathonService {

    Hackathon getHackathonById(Long id);
    void addParticipant(Long id, Long userId);

    Hackathons getAllHackathons();
}

@Service
@Transactional
@RequiredArgsConstructor
class HackathonServiceImpl implements HackathonService{

    private final UserService userService;
    private final HackathonRepository hackathonRepository;

    @Override
    public Hackathon getHackathonById(Long id) {
        return hackathonRepository.findById(id).orElseThrow();
    }

    @Override
    public void addParticipant(Long id, Long userId) {
        Hackathon hackathon = hackathonRepository.findById(id).orElseThrow();
        User user = userService.getUserById(userId);

        if (hackathon.getParticipants().contains(user))
            throw new ApiException(HttpStatus.BAD_REQUEST, "User has registered already.");

        Set<User> participants = hackathon.getParticipants();
        participants.add(user);
        hackathon.setParticipants(participants);

        hackathonRepository.save(hackathon);
    }

    @Override
    public Hackathons getAllHackathons() {
        return new Hackathons(hackathonRepository.findAll());
    }
}
