package com.MariaRamunno.Cookly.CookbookTests;

import com.MariaRamunno.Cookly.Cookbook.controller.CookbookController;
import com.MariaRamunno.Cookly.Cookbook.exceptions.CookbookNotFoundException;
import com.MariaRamunno.Cookly.Cookbook.model.Cookbook;
import com.MariaRamunno.Cookly.Cookbook.service.CookbookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
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
public class CookbookControllerTests {

    @MockBean
    private CookbookService mockCookbookService;

    @InjectMocks
    private CookbookController mockCookbookController;

    @Autowired
    private MockMvc mockMvc;

    private Cookbook inputCookbook;

    private Cookbook mockResponseCookbook;

    @BeforeEach
    public void setUp(){
        inputCookbook = new Cookbook();
        inputCookbook.setRecipes(Collections.emptySet());
        inputCookbook.setTitle("Title");
        inputCookbook.setId(1L);

        mockResponseCookbook = new Cookbook();
        mockResponseCookbook.setId(1L);
        mockResponseCookbook.setTitle("Mock Example");
        mockResponseCookbook.setRecipes(Collections.emptySet());
    }

    @Test
    public void createCookbookSuccess() throws Exception{
        BDDMockito.doReturn(mockResponseCookbook).when(mockCookbookService).createCookbook(inputCookbook);

        mockMvc.perform(MockMvcRequestBuilders.post("/cookbooks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(inputCookbook)))

                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    public void getCookbookByIdSuccess() throws Exception{
        Long id = 1L;
        BDDMockito.doReturn(mockResponseCookbook).when(mockCookbookService).getCookbookbyId(id);

        mockMvc.perform(MockMvcRequestBuilders.get("/cookbooks/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getCookbookByIdFail() throws Exception{
        Long id = 2L;
        BDDMockito.doThrow(new CookbookNotFoundException("Not found")).when(mockCookbookService).getCookbookbyId(id);

        mockMvc.perform(MockMvcRequestBuilders.get("/cookbooks/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateCookbookSuccess() throws Exception {
        Long id = 1L;
        BDDMockito.doReturn(mockResponseCookbook).when(mockCookbookService).updateCookbook(inputCookbook, id);

        mockMvc.perform(MockMvcRequestBuilders.put("/cookbooks/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputCookbook)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteCookbookSuccess() throws Exception {
        Long id = 1L;
        BDDMockito.doReturn(true).when(mockCookbookService).deleteCookbook(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/cookbooks/delete/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteQuizTempTestNotFound() throws Exception {
        Long id = 1L;
        BDDMockito.doThrow(new CookbookNotFoundException("Not Found")).when(mockCookbookService).deleteCookbook(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/cookbooks/delete/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
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
