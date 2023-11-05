package com.drocketeers.server;

import com.drocketeers.server.model.Team;
import com.drocketeers.server.model.User;
import com.drocketeers.server.repository.TeamRepository;
import com.drocketeers.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@RequiredArgsConstructor
public class StartupRunner implements ApplicationRunner {

    private final UserRepository userRepository;
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

        Set<User> team = new HashSet<>();
        Arrays.stream(usernames).forEach((u) -> {
            if(userRepository.findByUsername(u).isPresent()) return;
            User user = userRepository.saveAndFlush(new User(u, true));
            if (team.size() == 5) {
                teamRepository.save(Team.create(team));
                log.info("Creating team..." + team);
                team.clear();
            }
            team.add(user);
        } );


    }
}