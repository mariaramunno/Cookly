package com.MariaRamunno.Cookly.StepsTests;

import com.MariaRamunno.Cookly.Steps.controller.StepController;
import com.MariaRamunno.Cookly.Steps.model.Steps;
import com.MariaRamunno.Cookly.Steps.service.StepService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StepController.class)
public class StepsControllerTests {
    @MockBean
    private StepService stepService;

    @Autowired
    private MockMvc mockMvc;

    private Steps inputStep;

    private Steps mockStep;

    @BeforeEach
    public void setUp() {
        inputStep = new Steps();

        mockStep = new Steps();
        mockStep.setDescription("Step1");
    }

    @Test
    void createStepSuccess() throws Exception {
        when(stepService.createStep(any())).thenReturn(mockStep);

        mockMvc.perform(post("/steps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputStep)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(mockStep)));
    }

    @Test
    void getStepById() throws Exception {
        when(stepService.getStepById(1L)).thenReturn(mockStep);

        mockMvc.perform(get("/steps/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(mockStep)));
    }

    @Test
    void updateStepSuccess() throws Exception {

        when(stepService.updateStep(any())).thenReturn(mockStep);

        mockMvc.perform(put("/steps/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockStep)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(mockStep)));
    }

    @Test
    void deleteStepSuccess() throws Exception {
        mockMvc.perform(delete("/steps/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Step deleted successfully"));
    }

    @Test
    void getAllSteps() throws Exception {
        List<Steps> stepsList = Arrays.asList(new Steps(), new Steps());
        when(stepService.getSteps()).thenReturn(stepsList);

        mockMvc.perform(get("/steps"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(stepsList)));
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
