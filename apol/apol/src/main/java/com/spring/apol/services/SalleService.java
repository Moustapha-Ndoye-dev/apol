package com.spring.apol.services;

import com.spring.apol.api.dto.SalleDto;

import java.util.List;
import java.util.Optional;

public interface SalleService {
    SalleDto createSalle(SalleDto salleDto); // Créer une nouvelle salle
    SalleDto updateSalle(Long id, SalleDto salleDto); // Mettre à jour une salle existante
    SalleDto getSalleById(Long id); // Récupérer une salle par ID
    List<SalleDto> getAllSalles(); // Récupérer toutes les salles
    void deleteSalle(Long id); // Supprimer une salle par ID
}
