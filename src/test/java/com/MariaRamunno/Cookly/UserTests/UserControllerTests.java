package com.MariaRamunno.Cookly.UserTests;

import com.MariaRamunno.Cookly.User.controller.UserController;
import com.MariaRamunno.Cookly.User.model.User;
import com.MariaRamunno.Cookly.User.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {


    @MockBean
    private UserService mockUserService;

    @InjectMocks
    private UserController mockUserController;

    @Autowired
    private MockMvc mockMvc;

    private User inputUser;

    private User mockResponseUser;

    @BeforeEach
    public void setUp() {
        inputUser = new User();
        inputUser.setEmail("email");
        inputUser.setId(1L);
        inputUser.setFirstName("First");
        inputUser.setLastName("Last");
        inputUser.setCookbook(Collections.emptySet());
        inputUser.setPassword("password");

        mockResponseUser = new User();
        mockResponseUser.setEmail("email");
        mockResponseUser.setId(1L);
        mockResponseUser.setFirstName("First");
        mockResponseUser.setLastName("Last");
        mockResponseUser.setCookbook(Collections.emptySet());
        mockResponseUser.setPassword("password");
    }

    @Test
    public void createUserSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseUser).when(mockUserService).createUser(inputUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inputUser)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
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
