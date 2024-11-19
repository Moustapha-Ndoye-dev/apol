package com.spring.apol.api.dto;

import com.spring.apol.data.models.Salle;
import lombok.Data;

@Data
public class SalleDto {
    private Long id;
    private String nom;

    // Convertir le DTO en entité Salle
    public Salle toEntity() {
        Salle salle = new Salle();
        salle.setId(this.id);
        salle.setNom(this.nom);
        return salle;
    }

    // Convertir l'entité Salle en DTO
    public static SalleDto fromEntity(Salle salle) {
        SalleDto dto = new SalleDto();
        dto.setId(salle.getId());
        dto.setNom(salle.getNom());
        return dto;
    }
}
