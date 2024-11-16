package com.gestionEmployer.Web_atrio_test.controllers;

import com.gestionEmployer.Web_atrio_test.Entities.Emploi;
import com.gestionEmployer.Web_atrio_test.Entities.Personne;
import com.gestionEmployer.Web_atrio_test.dto.PersonneDTO;
import com.gestionEmployer.Web_atrio_test.services.PersonneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/personnes")
@Tag(name = "PersonneController", description = "Gestion des Personnes")
public class PersonneController {

    @Autowired
    private PersonneService personneService;
    private static final Logger logger = LoggerFactory.getLogger(PersonneController.class);


    @Operation(
            summary = "Créer une nouvelle personne",
            description = "Enregistre une personne de moins de 150 ans",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Personne créée avec succès",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Personne.class))),
                    @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<Personne> createPersonne(
          @Valid @RequestBody Personne personneDTO) {
        Personne personne = personneService.createPersonne(personneDTO);
        return ResponseEntity.ok(personne);
    }

    @Operation(
            summary = "Obtenir toutes les personnes",
            description = "Renvoie toutes les personnes enregistrées par ordre alphabétique avec leur âge et emploi(s) actuel(s)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des personnes récupérée avec succès",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<Personne>> getAllPersonnes() {
        return ResponseEntity.ok(personneService.getAllPersonnes());
    }

    @Operation(
            summary = "Obtenir toutes les personnes ayant travaillé pour une entreprise spécifique",
            description = "Renvoie toutes les personnes ayant travaillé pour l'entreprise donnée",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des personnes récupérée avec succès",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "404", description = "Aucune personne trouvée", content = @Content)
            }
    )
    @GetMapping("/entreprise/{nomEntreprise}")
    public ResponseEntity<List<Personne>> getPersonnesByEntreprise(
            @PathVariable String nomEntreprise) {
        List<Personne> personnes = personneService.getPersonnesByEntreprise(nomEntreprise);
        return ResponseEntity.ok(personnes);
    }

    @Operation(
            summary = "Ajouter un emploi à une personne",
            description = "Enregistre un emploi pour une personne spécifique",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Emploi ajouté avec succès"),
                    @ApiResponse(responseCode = "404", description = "Personne non trouvée", content = @Content)
            }
    )
    @PostMapping("/{personId}/emplois")
    public ResponseEntity<String> addEmploiToPersonne(
            @RequestBody Emploi emploiDTO,
            @PathVariable int personId) {
        logger.info("emploi "+ emploiDTO.getPoste());
        personneService.addEmploiToPersonne(emploiDTO, personId);
        return ResponseEntity.ok("L'emploi a été correctement affecté à la personne.");
    }

    @Operation(summary = "Supprimer une personne par ID",
            description = "Cette opération permet de supprimer une personne spécifiée par son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personne supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Personne non trouvée")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonne(@PathVariable("id") Long id) {
        if (personneService.deletePersonne(id)) {
            return ResponseEntity.ok("Personne supprimée avec succès");
        } else {
            return ResponseEntity.ok("Personne non trouvée");
        }
    }

    @Operation(summary = "Supprimer une personne par ID",
            description = "Cette opération permet de supprimer une personne spécifiée par son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personne supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Personne non trouvée")
    })
    @DeleteMapping("/{personneId}/emplois/{emploiId}")
    public ResponseEntity<String> deleteEmploi(
            @PathVariable("personneId") Long personneId,
            @PathVariable("emploiId") Long emploiId) {
        if (personneService.deleteEmploi(personneId, emploiId)) {
            return ResponseEntity.ok("Emploi supprimé avec succès");
        } else {
            return ResponseEntity.ok("Emploi ou personne non trouvée");
        }
    }
}
