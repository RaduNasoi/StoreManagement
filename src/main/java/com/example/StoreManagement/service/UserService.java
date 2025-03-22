package com.example.StoreManagement.service;

import com.example.StoreManagement.exceptions.UserNotFoundException;
import com.example.StoreManagement.model.User;
import com.example.StoreManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.StoreManagement.util.StoreManagementConstants.USER_NOT_FOUND_ERROR_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> {
            log.error("Attempted to get username {}", username);
            return new UserNotFoundException(USER_NOT_FOUND_ERROR_MESSAGE);
        });
    }
}
