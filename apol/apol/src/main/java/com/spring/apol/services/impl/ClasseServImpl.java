package com.spring.apol.services.impl;

import com.spring.apol.api.dto.ClasseDto;
import com.spring.apol.data.models.Classe;
import com.spring.apol.data.models.Filiere;
import com.spring.apol.data.repositories.ClasseRepository;
import com.spring.apol.data.repositories.FiliereRepository;
import com.spring.apol.services.ClasseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClasseServImpl implements ClasseService {

    private final ClasseRepository classeRepository;
    private final FiliereRepository filiereRepository;


    public ClasseServImpl(ClasseRepository classeRepository, FiliereRepository filiereRepository) {
        this.classeRepository = classeRepository;
        this.filiereRepository = filiereRepository;

    }

    @Override
    public ClasseDto createClasse(ClasseDto classeDto) {
        Classe classe = classeDto.toEntity(); // Convertir le DTO en entité

        // Si un ID de filière est fourni, récupérer et assigner la filière
        if (classeDto.getFiliereId() != null) {
            Filiere filiere = filiereRepository.findById(classeDto.getFiliereId())
                    .orElseThrow(() -> new RuntimeException("Filière non trouvée"));
            classe.setFiliere(filiere);
        }


        classe = classeRepository.save(classe); // Enregistrer l'entité
        return ClasseDto.fromEntity(classe); // Retourner le DTO de la classe créée
    }

    @Override
    public ClasseDto updateClasse(Long id, ClasseDto classeDto) {
        Classe existingClasse = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
        existingClasse.setNom(classeDto.getNom());
        existingClasse.setNiveau(classeDto.getNiveau());

        // Mise à jour de la filière associée
        if (classeDto.getFiliereId() != null) {
            Filiere filiere = filiereRepository.findById(classeDto.getFiliereId())
                    .orElseThrow(() -> new RuntimeException("Filiere non trouvée"));
            existingClasse.setFiliere(filiere);
        }


        classeRepository.save(existingClasse);
        return ClasseDto.fromEntity(existingClasse);
    }

    @Override
    public ClasseDto getClasseById(Long id) {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
        return ClasseDto.fromEntity(classe);
    }

    @Override
    public List<ClasseDto> getAllClasses() {
        return classeRepository.findAll().stream()
                .map(ClasseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClasse(Long id) {
        classeRepository.deleteById(id);
    }

    @Override
    public List<ClasseDto> getClassesByFiliereId(Long filiereId) {
        return classeRepository.findByFiliereId(filiereId).stream()
                .map(ClasseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
