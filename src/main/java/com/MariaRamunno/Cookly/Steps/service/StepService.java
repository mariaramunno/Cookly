package com.MariaRamunno.Cookly.Steps.service;

import com.MariaRamunno.Cookly.Steps.model.Steps;

import java.util.List;

public interface StepService {

    Steps createStep(Steps steps);
    List<Steps> getSteps();
    Steps getStepById(long id);
    Steps updateStep(Steps steps);
    void  deleteStep(long id);

}
