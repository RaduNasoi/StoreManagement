package com.example.StoreManagement.controller;

import com.example.StoreManagement.dto.LoginRequestDto;
import com.example.StoreManagement.model.User;
import com.example.StoreManagement.service.UserService;
import com.example.StoreManagement.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        Optional<User> user = userService.getUserByUsername(loginRequestDto.username());
        if (user.isPresent() && loginRequestDto.password().equals(user.get().getPassword())) {
            String token = jwtUtil.generateToken(user.get());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
