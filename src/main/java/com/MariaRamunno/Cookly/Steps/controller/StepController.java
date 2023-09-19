package com.MariaRamunno.Cookly.Steps.controller;

import com.MariaRamunno.Cookly.Steps.model.Steps;
import com.MariaRamunno.Cookly.Steps.service.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/steps")
@RequiredArgsConstructor
public class StepController {

    @Autowired
    private StepService stepService;

    @GetMapping
    public List<Steps> getSteps(){
        return stepService.getSteps();
    }

    @PostMapping
    public ResponseEntity<Steps> createStep(@RequestBody Steps steps){
        Steps createdSteps = stepService.createStep(steps);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSteps);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Steps> updateStep(@PathVariable long id, @RequestBody Steps steps) {
        Steps updatedStep= stepService.updateStep(steps);
        return ResponseEntity.ok(updatedStep);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStep(@PathVariable long id){
        stepService.deleteStep(id);
        return ResponseEntity.ok("Step deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Steps> getStepById(@PathVariable long id){
        Steps steps = stepService.getStepById(id);
        return ResponseEntity.ok(steps);
    }

}
