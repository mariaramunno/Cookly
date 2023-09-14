package com.MariaRamunno.Cookly.Cookbook.controller;

import com.MariaRamunno.Cookly.Cookbook.model.Cookbook;
import com.MariaRamunno.Cookly.Cookbook.service.CookbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cookbooks")
@RequiredArgsConstructor
public class CookbookController {

    @Autowired
    private CookbookService cookbookService;

    @GetMapping
    public List<Cookbook> getCookbooks(){
        return cookbookService.getCookbooks();
    }

    @PostMapping
    public ResponseEntity<Cookbook> createCookbook(@RequestBody Cookbook cookbook){
        Cookbook createdCookbook = cookbookService.createCookbook(cookbook);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCookbook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cookbook> updateCookbook(@PathVariable long id, @RequestBody Cookbook cookbook) {
        Cookbook updatedCookbook = cookbookService.updateCookbook(cookbook);
        return ResponseEntity.ok(updatedCookbook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCookbook(@PathVariable long id){
        cookbookService.deleteCookbook(id);
        return ResponseEntity.ok("Cookbook deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cookbook> getCookbookbyId(@PathVariable long id){
        Cookbook cookbook = cookbookService.getCookbookbyId(id);
        return ResponseEntity.ok(cookbook);
    }

}
