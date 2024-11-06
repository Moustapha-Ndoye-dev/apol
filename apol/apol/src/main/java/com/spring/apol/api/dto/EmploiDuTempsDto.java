package com.spring.apol.api.dto;

import com.spring.apol.data.models.EmploiDuTemps;
import lombok.Data;

import java.time.LocalTime;

@Data
public class EmploiDuTempsDto {
    private Long id; // Identifiant unique de l'emploi du temps
    private String jour; // Jour de la semaine pour lequel l'emploi du temps est défini
    private LocalTime heureDebut; // Heure de début de la classe
    private LocalTime heureFin; // Heure de fin de la classe
    private Long classeId; // ID de la classe associée
    private Long filiereId; // ID de la filière associée

    // Méthode pour convertir le DTO en entité EmploiDuTemps
    public EmploiDuTemps toEntity() {
        EmploiDuTemps emploiDuTemps = new EmploiDuTemps();
        emploiDuTemps.setId(this.id); // Assigne l'ID
        emploiDuTemps.setJour(this.jour); // Assigne le jour
        emploiDuTemps.setHeureDebut(this.heureDebut); // Assigne l'heure de début
        emploiDuTemps.setHeureFin(this.heureFin); // Assigne l'heure de fin

        // Vous pouvez ajouter la logique pour récupérer les entités des classes et des filières si nécessaire
        // Exemple d'affectation de classe et de filière (si vous avez les objets correspondants)

        return emploiDuTemps; // Retourne l'entité remplie
    }

    // Méthode statique pour convertir l'entité EmploiDuTemps en DTO
    public static EmploiDuTempsDto fromEntity(EmploiDuTemps emploiDuTemps) {
        EmploiDuTempsDto dto = new EmploiDuTempsDto(); // Crée un nouvel objet DTO
        dto.setId(emploiDuTemps.getId()); // Assigne l'ID de l'entité au DTO
        dto.setJour(emploiDuTemps.getJour()); // Assigne le jour
        dto.setHeureDebut(emploiDuTemps.getHeureDebut()); // Assigne l'heure de début
        dto.setHeureFin(emploiDuTemps.getHeureFin()); // Assigne l'heure de fin

        // Récupère les IDs des relations (classe et filière) si elles existent
        if (emploiDuTemps.getClasse() != null) {
            dto.setClasseId(emploiDuTemps.getClasse().getId()); // Assigne l'ID de la classe
        }
        if (emploiDuTemps.getFiliere() != null) {
            dto.setFiliereId(emploiDuTemps.getFiliere().getId()); // Assigne l'ID de la filière
        }

        return dto; // Retourne le DTO rempli
    }
}
