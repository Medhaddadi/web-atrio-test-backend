package com.gestionEmployer.Web_atrio_test.repository;

import com.gestionEmployer.Web_atrio_test.Entities.Emploi;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmploiRepository extends JpaRepository<Emploi, Long> {
}