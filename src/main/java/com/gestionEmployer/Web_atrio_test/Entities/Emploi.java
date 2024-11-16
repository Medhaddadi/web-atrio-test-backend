package com.gestionEmployer.Web_atrio_test.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Emploi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de l'entreprise est obligatoire")
    private String entreprise;

    @NotBlank(message = "Le poste est obligatoire")
    private String poste;

    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;


    private LocalDate dateFin;

    @PrePersist
    @PreUpdate
    public void validateDates() {
        if (dateFin != null && dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début.");
        }
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "personne_id", nullable = false)
    private Personne personne;

    @Override
    public String toString() {
        return "Emploi{" +
                "id=" + id +
                ", entreprise='" + entreprise + '\'' +
                ", poste='" + poste + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", personne=" + personne +
                '}';
    }
}
