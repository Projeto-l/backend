package com.medcom.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import com.medcom.entity.User;

import com.medcom.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(UUID.randomUUID());
        user.setName("Dr. Alice");
        user.setEmail("alice@example.com");
        user.setPassword("securepassword");
    }

    @Test
    void shouldFindAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        List<User> users = userService.findAll();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
    }

    @Test
    void shouldFindUserById() {
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.findById(user.getUserId());
        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    void shouldSaveUser() {
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.save(user);
        assertNotNull(savedUser);
        assertEquals(user.getName(), savedUser.getName());
    }

    @Test
    void shouldDeleteUserById() {
        doNothing().when(userRepository).deleteById(user.getUserId());
        assertDoesNotThrow(() -> userService.deleteById(user.getUserId()));
        verify(userRepository, times(1)).deleteById(user.getUserId());
    }
}