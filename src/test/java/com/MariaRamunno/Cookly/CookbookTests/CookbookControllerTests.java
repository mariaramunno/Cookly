package com.MariaRamunno.Cookly.CookbookTests;

import com.MariaRamunno.Cookly.Cookbook.model.Cookbook;
import com.MariaRamunno.Cookly.Cookbook.service.CookbookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@SpringBootTest
public class CookbookControllerTests {

    @MockBean
    private CookbookService mockCookbookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createCookbookSuccess() throws Exception{

        Cookbook newCookbook = new Cookbook();
        Cookbook createdCookbook = new Cookbook();
        createdCookbook.setId(1L);
        when(mockCookbookService.createCookbook(newCookbook)).thenReturn(createdCookbook);

        // Act and Assert
        mockMvc.perform(post("/cookbooks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newCookbook)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(createdCookbook)));
    }


    @Test
    void getCookbookByIdSuccess() throws Exception{
        Cookbook cookbook = new Cookbook();
        cookbook.setId(1L);
        when(mockCookbookService.getCookbookbyId(1L)).thenReturn(cookbook);

        mockMvc.perform(get("/cookbooks/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(cookbook)));
    }


    @Test
    void updateCookbookSuccess() throws Exception {
        Cookbook updateCookbook = new Cookbook();
        updateCookbook.setId(1L);
        when(mockCookbookService.updateCookbook(updateCookbook)).thenReturn(updateCookbook);

        mockMvc.perform(put("/cookbooks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateCookbook)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(updateCookbook)));
    }

    @Test
    void deleteCookbookSuccess() throws Exception {
        mockMvc.perform(delete("/cookbooks/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Profile deleted successfully"));
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
