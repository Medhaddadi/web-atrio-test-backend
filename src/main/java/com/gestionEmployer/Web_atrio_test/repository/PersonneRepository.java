package com.gestionEmployer.Web_atrio_test.repository;

import com.gestionEmployer.Web_atrio_test.Entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
    @Query("select p from Personne p join Emploi e on p.id=e.personne.id where e.entreprise = :nomEntreprise")
    List<Personne> findPersonnesByEntreprise(String nomEntreprise);
}