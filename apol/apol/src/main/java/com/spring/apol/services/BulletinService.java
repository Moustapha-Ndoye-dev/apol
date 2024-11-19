package com.spring.apol.services;

import com.spring.apol.api.dto.BulletinDto;
import java.util.List;

public interface BulletinService {
    // Méthode pour créer un bulletin
    BulletinDto createBulletin(BulletinDto bulletinDto);

    // Méthode pour obtenir un bulletin par son ID
    BulletinDto getBulletinById(Long id);

    // Méthode pour mettre à jour un bulletin
    BulletinDto updateBulletin(Long id, BulletinDto bulletinDto);

    // Méthode pour supprimer un bulletin
    boolean deleteBulletin(Long id);

    // Méthode pour obtenir tous les bulletins d'un étudiant
    List<BulletinDto> getBulletinsByEtudiantId(Long etudiantId);

    // Méthode pour obtenir tous les bulletins d'une classe
    List<BulletinDto> getBulletinsByClasseId(Long classeId);

    // Méthode pour obtenir tous les bulletins d'une filière
    List<BulletinDto> getBulletinsByFiliereId(Long filiereId);
}
