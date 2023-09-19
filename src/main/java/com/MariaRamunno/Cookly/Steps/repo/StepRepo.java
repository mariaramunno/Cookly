package com.MariaRamunno.Cookly.Steps.repo;

import com.MariaRamunno.Cookly.Steps.model.Steps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepRepo extends JpaRepository<Steps, Long> {
}
