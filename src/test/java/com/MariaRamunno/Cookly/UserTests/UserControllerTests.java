package com.MariaRamunno.Cookly.UserTests;

import com.MariaRamunno.Cookly.User.controller.UserController;
import com.MariaRamunno.Cookly.User.model.User;
import com.MariaRamunno.Cookly.User.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTests {


    @MockBean
    private UserService mockUserService;

    @Autowired
    private MockMvc mockMvc;

    private User inputUser;

    private User mockResponseUser;

    @BeforeEach
    public void setUp() {
        inputUser = new User();

        mockResponseUser = new User();
        mockResponseUser.setEmail("email");
        mockResponseUser.setFirstName("First");
        mockResponseUser.setLastName("Last");
        mockResponseUser.setCookbook(Collections.emptySet());
        mockResponseUser.setPassword("password");
    }

    @Test
    void createUserSuccess() throws Exception {
        when(mockUserService.createUser(any())).thenReturn(mockResponseUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputUser)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(mockResponseUser)));
    }

    @Test
    void getUserById() throws Exception {
        when(mockUserService.getUserbyId(1L)).thenReturn(mockResponseUser);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(mockResponseUser)));
    }

    @Test
    void updateUserSuccess() throws Exception {

        when(mockUserService.updateUser(any())).thenReturn(mockResponseUser);

        mockMvc.perform(put("/users/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(mockResponseUser)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(mockResponseUser)));
    }

    @Test
    void deleteUserSuccess() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted successfully"));
    }

    @Test
    void getAllUsers() throws Exception {
        List<User> userList = Arrays.asList(new User(), new User());
        when(mockUserService.getUsers()).thenReturn(userList);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(userList)));
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
            //ObjectMapper is a class that provides functionality for reading and writing JSON.
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
