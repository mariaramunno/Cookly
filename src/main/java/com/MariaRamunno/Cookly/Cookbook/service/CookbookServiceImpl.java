package com.MariaRamunno.Cookly.Cookbook.service;

import com.MariaRamunno.Cookly.Cookbook.model.Cookbook;
import com.MariaRamunno.Cookly.Cookbook.repo.CookbookRepo;
import com.MariaRamunno.Cookly.Cookbook.exceptions.CookbookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CookbookServiceImpl implements CookbookService{

    private final CookbookRepo cookbookRepo;
    @Override
    public Cookbook createCookbook(Cookbook cookbook) {
        return cookbookRepo.save(cookbook);
    }

    @Override
    public List<Cookbook> getCookbooks() {
        return cookbookRepo.findAll();
    }

    @Override
    public Cookbook getCookbookbyId(Long id) {
        return cookbookRepo.findById(id)
                .orElseThrow(() -> new CookbookNotFoundException("No cookbook found with this id: " + id));
    }

    @Override
    public Cookbook updateCookbook(Cookbook cookbook) {
        Cookbook newCookbook = cookbookRepo.findById(cookbook.getId())
                .orElseThrow(() -> new CookbookNotFoundException("Sorry, this cookbook could not be found."));

            newCookbook.setTitle(cookbook.getTitle());
            newCookbook.setRecipes(cookbook.getRecipes());
            newCookbook.setId(cookbook.getId());

            return cookbookRepo.save(newCookbook);
    }

    @Override
    public void deleteCookbook(Long id) {
        if(!cookbookRepo.existsById(id)){
            throw new CookbookNotFoundException("Sorry, cookbook not found. ");
        }
        cookbookRepo.deleteById(id);
    }
}
