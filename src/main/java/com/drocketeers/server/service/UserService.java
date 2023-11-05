package com.drocketeers.server.service;

import com.drocketeers.server.model.User;
import com.drocketeers.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User getUserById(Long id);
    User getUserByUsername(String username);
    List<User> getAllParticipants();

}

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Override
    public List<User> getAllParticipants() {
        return userRepository.getAllParticipants();
    }
}
