package com.drocketeers.server.service;

import com.drocketeers.server.dto.user.payload.UserData;
import com.drocketeers.server.exception.ApiException;
import com.drocketeers.server.model.User;
import com.drocketeers.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public interface UserService {

    void createUser(UserData userData);
    User getUserById(Long id);
    User getUserByUsername(String username);

    void deleteUserById(Long id);
}

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(UserData userData) {
        if (userRepository.findByUsername(userData.username()).isPresent()) throw new ApiException(HttpStatus.BAD_REQUEST, "Username must be unique.");

        String primaryEmail = userData.email_addresses().stream()
                .filter(e -> e.id().equals(userData.primary_email_address_id()))
                .findFirst().get().email_address();

        User user =  new User(
                userData.id(), userData.username(), primaryEmail,
                userData.birthday(), userData.last_name(),
                userData.first_name(), userData.image_url(),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(userData.created_at())), ZoneId.systemDefault())
        );

        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
