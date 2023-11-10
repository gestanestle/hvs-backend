package com.drocketeers.server.service;

import com.drocketeers.server.dto.user.payload.UserData;
import com.drocketeers.server.model.User;
import com.drocketeers.server.model.UserFactory;
import com.drocketeers.server.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    @Autowired
    private UserServiceImpl userService;

    @Captor
    ArgumentCaptor<User> captor;

    User user = UserFactory.createUser();

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void createUser() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(null));

        UserData userData = UserFactory.createUserData();
        userService.createUser(userData);

        verify(userRepository).save(captor.capture());
        assertThat(captor.getValue()).isEqualTo(user);
    }

    @Test
    void getUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
        assertThat(userService.getUserById(anyLong())).isEqualTo(user);
        verify(userRepository).findById(anyLong());
    }

    @Test
    void getUserByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(user));
        assertThat(userService.getUserByUsername(anyString())).isEqualTo(user);
        verify(userRepository).findByUsername(anyString());
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById(anyLong());
        verify(userRepository).deleteById(anyLong());
    }
}