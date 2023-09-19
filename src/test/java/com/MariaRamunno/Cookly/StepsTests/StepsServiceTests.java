package com.MariaRamunno.Cookly.StepsTests;

import com.MariaRamunno.Cookly.Steps.exceptions.StepNotFoundException;
import com.MariaRamunno.Cookly.Steps.model.Steps;
import com.MariaRamunno.Cookly.Steps.repo.StepRepo;
import com.MariaRamunno.Cookly.Steps.service.StepServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StepsServiceTests {

    @Mock
    private StepRepo stepRepo;

    @InjectMocks
    private StepServiceImpl stepService;

    @Test
    void getAllSteps() {
        List<Steps> steps = Arrays.asList(new Steps(), new Steps());
        when(stepRepo.findAll()).thenReturn(steps);

        List<Steps> result = stepService.getSteps();

        assertEquals(steps,result);
    }

    @Test
    public void createStepSuccess() {
        Steps steps = new Steps();
        when(stepRepo.save(steps)).thenReturn(steps);

        Steps result = stepService.createStep(steps);

        assertEquals(steps, result);
    }

    @Test
    public void updateStepSuccess() {
        long id = 1L;
        Steps existingStep = new Steps();
        existingStep.setId(id);
        Steps updatedStep = new Steps();
        updatedStep.setId(id);
        when(stepRepo.findById(id)).thenReturn(Optional.of(existingStep));
        when(stepRepo.save(existingStep)).thenReturn(updatedStep);

        Steps result = stepService.updateStep(updatedStep);

        assertEquals(updatedStep, result);
    }
    @Test
    void updateStepNotFound() {
        long id = 1L;
        Steps updatedStep= new Steps();
        updatedStep.setId(id);
        when(stepRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(StepNotFoundException.class, () -> {
            stepService.updateStep(updatedStep);
        });
    }

    @Test
    public void getStepByIdSuccess() {
        Steps steps = new Steps();
        long id = 1L;
        when(stepRepo.findById(id)).thenReturn(Optional.of(steps));

        Steps result = stepService.getStepById(id);

        assertEquals(steps, result);
    }

    @Test
    void getStepByIdNotFound() {
        long id = 1L;
        when(stepRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(StepNotFoundException.class, () -> {
            stepService.getStepById(id);
        });
    }
}
