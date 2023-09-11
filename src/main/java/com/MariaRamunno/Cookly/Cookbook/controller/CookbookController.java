package com.MariaRamunno.Cookly.Cookbook.controller;

import com.MariaRamunno.Cookly.Cookbook.model.Cookbook;
import com.MariaRamunno.Cookly.Cookbook.service.CookbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cookbooks")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CookbookController {

    private final CookbookService cookbookService;

    @GetMapping
    public ResponseEntity<List<Cookbook>> getCookbooks(){
        return new ResponseEntity<>(cookbookService.getCookbooks(), HttpStatus.FOUND);
    }

    @PostMapping
    public Cookbook createCookbook(@RequestBody Cookbook cookbook){
        return cookbookService.createCookbook(cookbook);
    }

    @PutMapping("/update/{id}")
    public Cookbook updateCookbook(@RequestBody Cookbook cookbook, @PathVariable Long id){
        return cookbookService.updateCookbook(cookbook, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCookbook(@PathVariable Long id){
        cookbookService.deleteCookbook(id);
    }

    @GetMapping("/{id}")
    public Cookbook getCookbookbyId(@PathVariable Long id){
        return cookbookService.getCookbookbyId(id);
    }

}
