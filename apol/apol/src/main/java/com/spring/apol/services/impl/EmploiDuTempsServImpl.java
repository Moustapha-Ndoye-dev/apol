package com.spring.apol.services.impl;

import com.spring.apol.api.dto.EmploiDuTempsDto;
import com.spring.apol.data.models.Classe;
import com.spring.apol.data.models.EmploiDuTemps;
import com.spring.apol.data.models.Filiere;
import com.spring.apol.data.repositories.ClasseRepository; // Assurez-vous d'importer le repository approprié
import com.spring.apol.data.repositories.FiliereRepository; // Assurez-vous d'importer le repository approprié
import com.spring.apol.data.repositories.EmploiDuTempsRepository;
import com.spring.apol.services.EmploiDuTempsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmploiDuTempsServImpl implements EmploiDuTempsService {

    private final EmploiDuTempsRepository emploiDuTempsRepository;

    private final ClasseRepository classeRepository; // Injecter le repository Classe

    private final FiliereRepository filiereRepository; // Injecter le repository Filiere

    public EmploiDuTempsServImpl(EmploiDuTempsRepository emploiDuTempsRepository, ClasseRepository classeRepository, FiliereRepository filiereRepository) {
        this.emploiDuTempsRepository = emploiDuTempsRepository;
        this.classeRepository = classeRepository;
        this.filiereRepository = filiereRepository;
    }

    @Override
    public EmploiDuTempsDto createEmploiDuTemps(EmploiDuTempsDto emploiDuTempsDto) {
        EmploiDuTemps emploiDuTemps = emploiDuTempsDto.toEntity(); // Convertir DTO en entité
        emploiDuTemps = emploiDuTempsRepository.save(emploiDuTemps); // Enregistrer l'entité
        return EmploiDuTempsDto.fromEntity(emploiDuTemps); // Convertir l'entité enregistrée en DTO
    }

    @Override
    public EmploiDuTempsDto updateEmploiDuTemps(Long id, EmploiDuTempsDto emploiDuTempsDto) {
        EmploiDuTemps existingEmploiDuTemps = emploiDuTempsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emploi du temps non trouvé"));

        existingEmploiDuTemps.setJour(emploiDuTempsDto.getJour());
        existingEmploiDuTemps.setHeureDebut(emploiDuTempsDto.getHeureDebut());
        existingEmploiDuTemps.setHeureFin(emploiDuTempsDto.getHeureFin());

        // Chercher la classe existante par ID
        if (emploiDuTempsDto.getClasseId() != null) {
            Optional<Classe> classe = classeRepository.findById(emploiDuTempsDto.getClasseId());
            existingEmploiDuTemps.setClasse(classe.orElseThrow(() -> new RuntimeException("Classe non trouvée")));
        } else {
            existingEmploiDuTemps.setClasse(null); // Si pas d'ID de classe, set null
        }

        // Chercher la filière existante par ID
        if (emploiDuTempsDto.getFiliereId() != null) {
            Optional<Filiere> filiere = filiereRepository.findById(emploiDuTempsDto.getFiliereId());
            existingEmploiDuTemps.setFiliere(filiere.orElseThrow(() -> new RuntimeException("Filière non trouvée")));
        } else {
            existingEmploiDuTemps.setFiliere(null); // Si pas d'ID de filière, set null
        }

        emploiDuTempsRepository.save(existingEmploiDuTemps);
        return EmploiDuTempsDto.fromEntity(existingEmploiDuTemps); // Retourner le DTO mis à jour
    }

    @Override
    public EmploiDuTempsDto getEmploiDuTempsById(Long id) {
        EmploiDuTemps emploiDuTemps = emploiDuTempsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emploi du temps non trouvé"));
        return EmploiDuTempsDto.fromEntity(emploiDuTemps); // Retourner le DTO correspondant
    }

    @Override
    public List<EmploiDuTempsDto> getAllEmploisDuTemps() {
        return emploiDuTempsRepository.findAll().stream()
                .map(EmploiDuTempsDto::fromEntity) // Convertir chaque entité en DTO
                .collect(Collectors.toList()); // Collecter les DTOs dans une liste
    }

    @Override
    public void deleteEmploiDuTemps(Long id) {
        emploiDuTempsRepository.deleteById(id); // Supprimer l'emploi du temps par ID
    }

    @Override
    public List<EmploiDuTempsDto> getEmploisDuTempsByClasseId(Long classeId) {
        return emploiDuTempsRepository.findByClasseId(classeId).stream()
                .map(EmploiDuTempsDto::fromEntity) // Convertir chaque entité en DTO
                .collect(Collectors.toList()); // Collecter les DTOs dans une liste
    }

    @Override
    public List<EmploiDuTempsDto> getEmploisDuTempsByFiliereId(Long filiereId) {
        return emploiDuTempsRepository.findByFiliereId(filiereId).stream()
                .map(EmploiDuTempsDto::fromEntity) // Convertir chaque entité en DTO
                .collect(Collectors.toList()); // Collecter les DTOs dans une liste
    }
}
