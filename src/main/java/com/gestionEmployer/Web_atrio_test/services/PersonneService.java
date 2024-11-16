package com.gestionEmployer.Web_atrio_test.services;

import com.gestionEmployer.Web_atrio_test.Entities.Emploi;
import com.gestionEmployer.Web_atrio_test.Entities.Personne;
import com.gestionEmployer.Web_atrio_test.dto.PersonneDTO;
import com.gestionEmployer.Web_atrio_test.repository.EmploiRepository;
import com.gestionEmployer.Web_atrio_test.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private EmploiRepository emploiRepository;

    public Personne createPersonne(Personne personneDTO) {
        return personneRepository.save(personneDTO);
    }


    public List<Personne> getAllPersonnes() {
        List<Personne> personnes = personneRepository.findAll();
        if (personnes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune personne trouvée.");
        }
        return personnes;
    }


    public List<Personne> getPersonnesByEntreprise(String nomEntreprise) {
        List<Personne> personnes = personneRepository.findPersonnesByEntreprise(nomEntreprise);
        if (personnes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune personne trouvée.");
        }
        return personnes;
    }

    public void addEmploiToPersonne(Emploi emploiDTO, Integer personId) {
        Optional<Personne> optionalPersonne = personneRepository.findById(personId.longValue());

        if (optionalPersonne.isEmpty()) {
            throw new IllegalArgumentException("Personne avec ID " + personId + " introuvable");
        }

        Personne personne = optionalPersonne.get();

        emploiDTO.setPersonne(personne);
        Emploi savedEmploi = emploiRepository.save(emploiDTO);

        personne.getEmplois().add(savedEmploi);

        personneRepository.save(personne);
    }

    public boolean deletePersonne(Long id) {
        Optional<Personne> personne = personneRepository.findById(id);
        if (personne.isPresent()) {
            personneRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteEmploi(Long personneId, Long emploiId) {
        Optional<Personne> personne = personneRepository.findById(personneId);
        if (personne.isPresent()) {
            Optional<Emploi> emploi = emploiRepository.findById(emploiId);
            if (emploi.isPresent() && emploi.get().getPersonne().getId().equals(personneId)) {
                emploiRepository.deleteById(emploiId);
                return true;
            }
        }
        return false;
    }
}
