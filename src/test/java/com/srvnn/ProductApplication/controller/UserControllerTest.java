package com.srvnn.ProductApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srvnn.ProductApplication.model.RegisterRequest;
import com.srvnn.ProductApplication.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testUserRegister() throws Exception {
        UUID uuid = UUID.randomUUID();
        RegisterRequest request = new RegisterRequest("John", "Doe");

        when(userService.registerUser(any(RegisterRequest.class))).thenReturn(uuid);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(uuid.toString()));
    }

    @Test
    void testUserRegister_throwException() throws Exception {
        UUID uuid = UUID.randomUUID();
        RegisterRequest request = new RegisterRequest("John", "Doe");

        when(userService.registerUser(any(RegisterRequest.class))).thenThrow();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isNotFound()
                );
    }


}
