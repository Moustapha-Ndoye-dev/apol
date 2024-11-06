package com.spring.apol.api.dto;

import com.spring.apol.data.models.Classe;
import com.spring.apol.data.models.Utilisateur;
import com.spring.apol.data.models.Filiere;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FiliereDto {
    private Long id;
    private String nom;
    private List<Long> classesIds; // Liste des ID des classes associées
    private List<Long> professeursIds; // Liste des ID des professeurs associés

    public Filiere toEntity() {
        Filiere filiere = new Filiere();
        filiere.setId(this.id);
        filiere.setNom(this.nom);
        // Vous pouvez ajouter la logique pour récupérer les entités des professeurs et des classes si nécessaire
        return filiere;
    }

    public static FiliereDto fromEntity(Filiere filiere) {
        FiliereDto dto = new FiliereDto();
        dto.setId(filiere.getId());
        dto.setNom(filiere.getNom());


        return dto;
    }
}
