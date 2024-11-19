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
        // Ici, vous pouvez ajouter la logique pour récupérer les entités des professeurs et des classes si nécessaire
        return module;
    }

    public static ModuleDto fromEntity(Module module) {
        ModuleDto dto = new ModuleDto();
        dto.setId(module.getId());
        dto.setNom(module.getNom());

        return dto;
    }
}
