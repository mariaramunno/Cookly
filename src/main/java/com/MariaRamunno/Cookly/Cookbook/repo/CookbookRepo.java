package com.MariaRamunno.Cookly.Cookbook.repo;

import com.MariaRamunno.Cookly.Cookbook.model.Cookbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CookbookRepo extends JpaRepository<Cookbook, Long> {
}
