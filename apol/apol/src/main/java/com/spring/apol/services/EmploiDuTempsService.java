package com.spring.apol.services;

import com.spring.apol.api.dto.EmploiDuTempsDto;

import java.util.List;

public interface EmploiDuTempsService {
    // Méthode pour créer un nouvel emploi du temps
    EmploiDuTempsDto createEmploiDuTemps(EmploiDuTempsDto emploiDuTempsDto);

    // Méthode pour mettre à jour un emploi du temps existant
    EmploiDuTempsDto updateEmploiDuTemps(Long id, EmploiDuTempsDto emploiDuTempsDto);

    // Méthode pour récupérer un emploi du temps par ID
    EmploiDuTempsDto getEmploiDuTempsById(Long id);

    // Méthode pour récupérer tous les emplois du temps
    List<EmploiDuTempsDto> getAllEmploisDuTemps();

    // Méthode pour supprimer un emploi du temps par ID
    void deleteEmploiDuTemps(Long id);

    // Méthode pour récupérer les emplois du temps par classe
    List<EmploiDuTempsDto> getEmploisDuTempsByClasseId(Long classeId);

    // Méthode pour récupérer les emplois du temps par filière
    List<EmploiDuTempsDto> getEmploisDuTempsByFiliereId(Long filiereId);
}
