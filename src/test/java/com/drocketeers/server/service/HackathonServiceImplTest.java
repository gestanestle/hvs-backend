package com.drocketeers.server.service;

import com.drocketeers.server.model.Hackathon;
import com.drocketeers.server.model.HackathonFactory;
import com.drocketeers.server.model.User;
import com.drocketeers.server.model.UserFactory;
import com.drocketeers.server.repository.HackathonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HackathonServiceImplTest {

    @Mock
    private UserService userService;
    @Mock
    private HackathonRepository hackathonRepository;
    @InjectMocks @Autowired
    private HackathonServiceImpl hackathonService;
    @Captor
    ArgumentCaptor<Hackathon> captor;
    Hackathon hackathon = HackathonFactory.createHackathon();

    @BeforeEach
    void setUp() {
        hackathonService = new HackathonServiceImpl(userService, hackathonRepository);
    }

    @Test
    void getHackathonById() {
        when(hackathonRepository.findById(anyLong())).thenReturn(Optional.of(hackathon));
        assertThat(hackathonService.getHackathonById(anyLong())).isEqualTo(hackathon);
        verify(hackathonRepository).findById(anyLong());
    }

    @Test
    void addParticipant() {

        // Given
        User user = UserFactory.createUser();
        when(hackathonRepository.findById(anyLong())).thenReturn(Optional.ofNullable(hackathon));
        when(userService.getUserById(anyLong())).thenReturn(user);

        // Then
        hackathonService.addParticipant(1L, 1L);

        Set<User> participants = hackathon.getParticipants();
        participants.add(user);
        hackathon.setParticipants(participants);

        verify(hackathonRepository).save(captor.capture());
        assertThat(captor.getValue()).isEqualTo(hackathon);
    }

    @Test
    void getAllHackathons() {
        when(hackathonRepository.findAll()).thenReturn(List.of(hackathon));
        hackathonService.getAllHackathons();
        verify(hackathonRepository).findAll();
    }
}