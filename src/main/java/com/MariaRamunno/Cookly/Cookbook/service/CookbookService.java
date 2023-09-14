package com.MariaRamunno.Cookly.Cookbook.service;

import com.MariaRamunno.Cookly.Cookbook.model.Cookbook;


import java.util.List;

public interface CookbookService {
    Cookbook createCookbook(Cookbook cookbook);
    List<Cookbook> getCookbooks();
    Cookbook getCookbookbyId(long id);
    Cookbook updateCookbook(Cookbook cookbook);
    void deleteCookbook(long id);
}
