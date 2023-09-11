package com.MariaRamunno.Cookly.CookbookTests;

import com.MariaRamunno.Cookly.Cookbook.model.Cookbook;
import com.MariaRamunno.Cookly.Cookbook.repo.CookbookRepo;
import com.MariaRamunno.Cookly.Cookbook.service.CookbookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CookbookServiceTests {

    @Mock
    private CookbookRepo cookbookRepo;

    @InjectMocks
    private CookbookServiceImpl cookbookService;

    private Cookbook inputCookbook;

    private Cookbook mockCookbook1;
    private Cookbook mockCookbook2;

    private Long id1;
    private Long id2;

    @BeforeEach
    public void setUp(){
        id1 = 1L;
        id2 = 2L;
        inputCookbook = new Cookbook();
        inputCookbook.setRecipes(Collections.emptySet());
        inputCookbook.setTitle("Title");

        mockCookbook1 = new Cookbook();
        mockCookbook1.setId(id1);
        mockCookbook1.setTitle("Mock Example 1");
        mockCookbook1.setRecipes(Collections.emptySet());

        mockCookbook2 = new Cookbook();
        mockCookbook2.setId(id2);
        mockCookbook2.setTitle("Mock Example 2");
        mockCookbook2.setRecipes(Collections.emptySet());
    }

    @Test
    public void createCookbookSuccess() {
        BDDMockito.doReturn(mockCookbook1).when(cookbookRepo).save(ArgumentMatchers.any());

        Cookbook returnedCookbook = cookbookService.createCookbook(inputCookbook);

        Assertions.assertNotNull(returnedCookbook, "Cookbook should not be null");
    }

    @Test
    public void updateCookbookSuccess() {
        BDDMockito.doReturn(Optional.of(mockCookbook1)).when(cookbookRepo).findById((Long) ArgumentMatchers.any());

        Cookbook updatedCookbook = cookbookService.updateCookbook(inputCookbook, id1);

        Assertions.assertEquals(updatedCookbook, mockCookbook1);
    }

    @Test
    public void getCookbookByIdSuccess() {
        BDDMockito.doReturn(Optional.of(mockCookbook1)).when(cookbookRepo).findById(id1);

        Cookbook returnedCookbook = cookbookService.getCookbookbyId(id1);

        Assertions.assertEquals(returnedCookbook, mockCookbook1);
    }
}
