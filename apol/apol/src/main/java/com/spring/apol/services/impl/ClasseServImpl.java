package com.spring.apol.services.impl;

import com.spring.apol.api.dto.ClasseDto;
import com.spring.apol.data.models.Classe;

import com.spring.apol.data.repositories.ClasseRepository;
import com.spring.apol.services.ClasseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClasseServImpl implements ClasseService {

    private final ClasseRepository classeRepository;

    public ClasseServImpl(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;

    }

    @Override
    public ClasseDto createClasse(ClasseDto classeDto) {
        Classe classe = classeDto.toEntity(); // Convertir le DTO en entité
        classe = classeRepository.save(classe); // Enregistrer l'entité
        return ClasseDto.fromEntity(classe); // Retourner le DTO de la classe créée
    }

    @Override
    public ClasseDto updateClasse(Long id, ClasseDto classeDto) {
        Classe existingClasse = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
        existingClasse.setNom(classeDto.getNom());
        existingClasse.setNiveau(classeDto.getNiveau());
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
    public boolean deleteClasse(Long id) {
        classeRepository.deleteById(id);
        return false;
    }


}
