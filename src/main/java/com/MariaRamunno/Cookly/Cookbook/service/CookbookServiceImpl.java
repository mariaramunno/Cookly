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
    public Cookbook updateCookbook(Cookbook cookbook, Long id) {
        return cookbookRepo.findById(id).map(st -> {

            st.setTitle(cookbook.getTitle());
            st.setRecipes(cookbook.getRecipes());
            st.setId(cookbook.getId());

            return cookbookRepo.save(st);
        }).orElseThrow(() -> new CookbookNotFoundException("Sorry, this cookbook could not be found."));
    }

    @Override
    public void deleteCookbook(Long id) {
        if(!cookbookRepo.existsById(id)){
            throw new CookbookNotFoundException("Sorry, cookbook not found. ");
        }
        cookbookRepo.deleteById(id);
    }
}
