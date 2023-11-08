package com.drocketeers.server;

import com.drocketeers.server.model.Team;
import com.drocketeers.server.model.Participant;
import com.drocketeers.server.model.User;
import com.drocketeers.server.repository.ParticipantRepository;
import com.drocketeers.server.repository.TeamRepository;
import com.drocketeers.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.*;

@Configuration
@RequiredArgsConstructor
public class StartupRunner implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;
    private final TeamRepository teamRepository;

    Logger log = LoggerFactory.getLogger(StartupRunner.class);
    @Override
    public void run(ApplicationArguments args) {
        log.info("Startup Runner running...");

        String [] usernames = {
                "krimeu", "benok", "MacAngel23", "Annie", "boybee",
                "andite", "toney010319", "BadPapi", "HOTDOG", "angry_talong",
                "DDruan19", "dembahhhhh", "matchu", "Luffy", "Benar",
                "FluffyBuddy", "Oshi", "Akini", "krispi_cream", "erika",
                "shelledfish", "getgian", "thermo_ecs", "asbeelzebub", "dev.enigma"
        };

        Set<Participant> team = new HashSet<>();
        Arrays.stream(usernames).forEach((u) -> {
            log.info("=========" + userRepository.findByUsername(u).isEmpty());
            if(userRepository.findByUsername("mock_data".concat(u)).isPresent()) return;
            User user = userRepository.saveAndFlush(new User(null, ("mock_data").concat(u), null, null, null, null, null, LocalDateTime.now()));
            Participant participant = participantRepository.saveAndFlush(new Participant(user));
            if (team.size() == 5) {
                teamRepository.save(Team.create("team_name", team));
                log.info("Creating team..." + team);
                team.clear();
            }
            team.add(participant);
        } );

        userRepository.findAll().forEach(user -> log.info(user.getUsername()));

    }
}