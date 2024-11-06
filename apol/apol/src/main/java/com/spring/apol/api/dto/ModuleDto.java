package com.spring.apol.api.dto;

import com.spring.apol.data.models.Classe;
import com.spring.apol.data.models.Module;
import com.spring.apol.data.models.Utilisateur;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ModuleDto {
    private Long id;
    private String nom;
    private String description;
    private List<Long> professeursIds; // Liste des ID des professeurs associés
    private List<Long> classesIds; // Liste des ID des classes associées

    public Module toEntity() {
        Module module = new Module();
        module.setId(this.id);
        module.setNom(this.nom);
        module.setDescription(this.description);
        // Ici, vous pouvez ajouter la logique pour récupérer les entités des professeurs et des classes si nécessaire
        return module;
    }

    public static ModuleDto fromEntity(Module module) {
        ModuleDto dto = new ModuleDto();
        dto.setId(module.getId());
        dto.setNom(module.getNom());
        dto.setDescription(module.getDescription());

        // Récupérer les IDs des professeurs et des classes
        dto.setProfesseursIds(module.getProfesseurs().stream()
                .map(Utilisateur::getId)
                .collect(Collectors.toList()));

        dto.setClassesIds(module.getClasses().stream()
                .map(Classe::getId)
                .collect(Collectors.toList()));

        return dto;
    }
}
