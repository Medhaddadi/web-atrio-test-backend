package com.gestionEmployer.Web_atrio_test.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestionEmployer.Web_atrio_test.Utils.AgeCalculator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OrderBy
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateNaissance;

    @PrePersist
    public void validateAge() {
        if (AgeCalculator.getAge(this.dateNaissance) > 150) {
            throw new IllegalArgumentException("L'âge doit être inférieur à 150 ans.");
        }
    }

    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL)
    private List<Emploi> Emplois;
}
