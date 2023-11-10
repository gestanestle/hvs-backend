package com.drocketeers.server;

import com.drocketeers.server.model.Hackathon;
import com.drocketeers.server.model.Team;
import com.drocketeers.server.model.User;
import com.drocketeers.server.repository.HackathonRepository;
import com.drocketeers.server.repository.TeamRepository;
import com.drocketeers.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@Configuration
@RequiredArgsConstructor
public class StartupRunner implements ApplicationRunner {

    private final UserRepository userRepository;
    private final HackathonRepository hackathonRepository;
    private final TeamRepository teamRepository;

    Logger log = LoggerFactory.getLogger(StartupRunner.class);
    @Override
    public void run(ApplicationArguments args) {
        log.info("Startup Runner running...");

        if (hackathonRepository.getBySeason(1).isPresent()) return;

        String [] usernames = {
                "krimeu", "benok", "MacAngel23", "Annie", "boybee",
                "andite", "toney010319", "BadPapi", "HOTDOG", "angry_talong",
                "DDruan19", "dembahhhhh", "matchu", "Luffy", "Benar",
                "FluffyBuddy", "Oshi", "Akini", "krispi_cream", "erika",
                "shelledfish", "getgian", "thermo_ecs", "asbeelzebub", "dev.enigma"
        };

        Hackathon hackathonS1 = hackathonRepository.saveAndFlush(
                Hackathon.create("Hackathon Season 1", 1,
                        LocalDateTime.of(2023, Month.OCTOBER, 31, 0, 0),
                        LocalDateTime.of(2023, Month.NOVEMBER, 4, 0, 0),
                        LocalDateTime.of(2023, Month.NOVEMBER, 12, 0, 0),
                        "Description of season 1 of Daedalus Hackathon",
                        null));

        String [] teamNames = {"Team 1", "Team 2", "Team 3", "Team 4", "Team 5"};
        Deque<String> q = new ArrayDeque<>(Arrays.asList(teamNames));

        Set<User> participants = hackathonS1.getParticipants();
        Set<User> team = new HashSet<>();

        Arrays.stream(usernames).forEach((u) -> {
            String username = ("mock_data_").concat(u);

            log.info(String.valueOf(userRepository.findByUsername(username).isPresent()));

            User user = userRepository.findByUsername(username).isPresent()
                    ? userRepository.findByUsername(username).get()
                    : userRepository.saveAndFlush(new User(null, username,
                    null, null, null, null, null, LocalDateTime.now()));

            participants.add(user);

            if (team.size() == 5) {
                teamRepository.save(Team.create(q.peekFirst(), hackathonS1, team));
                log.info("Creating team..." + team);
                team.clear();
                q.removeFirst();
            }
            team.add(user);
        } );

        hackathonS1.setParticipants(participants);
        hackathonRepository.save(hackathonS1);

        if (hackathonRepository.getBySeason(2).isPresent()) return;

        Hackathon hackathonS2 = hackathonRepository.saveAndFlush(
                Hackathon.create("Hackathon Season 2", 2,
                        LocalDateTime.of(2024, Month.JANUARY, 31, 0, 0),
                        LocalDateTime.of(2024, Month.FEBRUARY, 4, 0, 0),
                        LocalDateTime.of(2024, Month.FEBRUARY, 12, 0, 0),
                        "Description of season 2 of Daedalus Hackathon",
                        null));

        hackathonRepository.save(hackathonS2);
    }
}