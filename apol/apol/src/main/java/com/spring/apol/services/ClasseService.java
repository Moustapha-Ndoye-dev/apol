package com.spring.apol.services;

import com.spring.apol.api.dto.ClasseDto;

import java.util.List;

public interface ClasseService {
    ClasseDto createClasse(ClasseDto classeDto); // Créer une nouvelle classe
    ClasseDto updateClasse(Long id, ClasseDto classeDto); // Mettre à jour une classe existante
    ClasseDto getClasseById(Long id); // Récupérer une classe par ID
    List<ClasseDto> getAllClasses(); // Récupérer toutes les classes
    void deleteClasse(Long id); // Supprimer une classe par ID
    List<ClasseDto> getClassesByFiliereId(Long filiereId); // Récupérer les classes par ID de filière
}
