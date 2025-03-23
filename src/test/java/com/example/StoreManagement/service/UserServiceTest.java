package com.example.StoreManagement.service;

import com.example.StoreManagement.exceptions.UserNotFoundException;
import com.example.StoreManagement.model.User;
import com.example.StoreManagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnUserWhenUsernameExists() {
        // given
        String username = "john_doe";
        User user = new User();
        user.setId(1L);
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // when
        User result = userService.getUserByUsername(username);

        // then
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(userRepository).findByUsername(username);
    }

    @Test
    void shouldThrowExceptionWhenUsernameNotFound() {
        // given
        String username = "unknown_user";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> userService.getUserByUsername(username))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found");

        verify(userRepository).findByUsername(username);
    }

}