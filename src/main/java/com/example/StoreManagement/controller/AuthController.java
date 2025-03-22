package com.example.StoreManagement.controller;

import com.example.StoreManagement.dto.LoginRequestDto;
import com.example.StoreManagement.model.User;
import com.example.StoreManagement.service.UserService;
import com.example.StoreManagement.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        User user = userService.getUserByUsername(loginRequestDto.username());
        if (user != null && loginRequestDto.password().equals(user.getPassword())) {
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
