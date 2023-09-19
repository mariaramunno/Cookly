package com.MariaRamunno.Cookly.Steps.service;

import com.MariaRamunno.Cookly.Steps.exceptions.StepNotFoundException;
import com.MariaRamunno.Cookly.Steps.model.Steps;
import com.MariaRamunno.Cookly.Steps.repo.StepRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StepServiceImpl implements StepService{

    private final StepRepo stepRepo;
    @Override
    public Steps createStep(Steps steps) {
        return stepRepo.save(steps);
    }

    @Override
    public List<Steps> getSteps() {
        return stepRepo.findAll();
    }

    @Override
    public Steps getStepById(long id) {
        return stepRepo.findById(id)
                .orElseThrow(() -> new StepNotFoundException("No step found with this id:" + id));
    }

    @Override
    public Steps updateStep(Steps steps) {
        Steps newSteps = stepRepo.findById(steps.getId())
                .orElseThrow(() -> new StepNotFoundException("Sorry, this step could not be found."));

        newSteps.setDescription(steps.getDescription());

        return stepRepo.save(newSteps);
    }

    @Override
    public void deleteStep(long id) {
        if(!stepRepo.existsById(id)){
            throw new StepNotFoundException("Sorry, no step found.");
        }
        stepRepo.deleteById(id);
    }
}
