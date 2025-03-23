package com.example.StoreManagement.integrations;

import com.example.StoreManagement.service.UserService;
import com.example.StoreManagement.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Test
    void shouldReturnJwtTokenForValidCredentials() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "username": "admin",
                                "password": "admin123"
                            }
                            """))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404ForInvalidCredentials() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "username": "wrong",
                                "password": "wrong"
                            }
                            """))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400ForInputValidation() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "username": "",
                                "password": "nope"
                            }
                            """))
                .andExpect(status().isBadRequest());
    }
}
