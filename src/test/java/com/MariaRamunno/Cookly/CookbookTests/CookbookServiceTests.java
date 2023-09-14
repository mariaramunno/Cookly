package com.MariaRamunno.Cookly.CookbookTests;

import com.MariaRamunno.Cookly.Cookbook.exceptions.CookbookNotFoundException;
import com.MariaRamunno.Cookly.Cookbook.model.Cookbook;
import com.MariaRamunno.Cookly.Cookbook.repo.CookbookRepo;
import com.MariaRamunno.Cookly.Cookbook.service.CookbookServiceImpl;
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
public class CookbookServiceTests {

    @Mock
    private CookbookRepo cookbookRepo;

    @InjectMocks
    private CookbookServiceImpl cookbookService;

    @Test
    void getAllCookbooks() {
        List<Cookbook> cookbooks = Arrays.asList(new Cookbook(), new Cookbook());
        when(cookbookRepo.findAll()).thenReturn(cookbooks);

        List<Cookbook> result = cookbookService.getCookbooks();

        assertEquals(cookbooks, result);
    }
    @Test
    public void createCookbookSuccess() {
        Cookbook cookbook = new Cookbook();
        when(cookbookRepo.save(cookbook)).thenReturn(cookbook);

        Cookbook result = cookbookService.createCookbook(cookbook);

        assertEquals(cookbook, result);
    }

    @Test
    public void updateCookbookSuccess() {
        long id = 1L;
        Cookbook existingCookbook = new Cookbook();
        existingCookbook.setId(id);
        Cookbook updatedCookbook = new Cookbook();
        updatedCookbook.setId(id);
        when(cookbookRepo.findById(id)).thenReturn(Optional.of(existingCookbook));
        when(cookbookRepo.save(existingCookbook)).thenReturn(updatedCookbook);

        Cookbook result = cookbookService.updateCookbook(updatedCookbook);

        assertEquals(updatedCookbook, result);
    }

    @Test
    void updateCookbookNotFound() {
        long id = 1L;
        Cookbook updatedCookbook = new Cookbook();
        updatedCookbook.setId(id);
        when(cookbookRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(CookbookNotFoundException.class, () -> {
            cookbookService.updateCookbook(updatedCookbook);
        });
    }
    @Test
    public void getCookbookByIdSuccess() {
        Cookbook cookbook = new Cookbook();
        long id = 1L;
        when(cookbookRepo.findById(id)).thenReturn(Optional.of(cookbook));

        Cookbook result = cookbookService.getCookbookbyId(id);

        assertEquals(cookbook, result);
    }

    @Test
    void getCookbookByIdNotFound() {
        long id = 1L;
        when(cookbookRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(CookbookNotFoundException.class, () -> {
            cookbookService.getCookbookbyId(id);
        });
    }
}
