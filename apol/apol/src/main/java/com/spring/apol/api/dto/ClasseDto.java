package com.spring.apol.api.dto;

import com.spring.apol.data.enums.Niveau;
import com.spring.apol.data.models.Classe;
import lombok.Data;


@Data
public class ClasseDto {
    private Long id; // Identifiant unique de la classe
    private String nom; // Nom de la classe
    private Niveau niveau; // Niveau de la classe
    // Constructeur par défaut
    public ClasseDto() {}

    // Constructeur avec paramètres
    public ClasseDto(Long id, String nom, Niveau niveau) {
        this.id = id;
        this.nom = nom;
        this.niveau = niveau;
    }

    // Méthode pour créer un DTO à partir d'une entité Classe
    public static ClasseDto fromEntity(Classe classe) {
        ClasseDto dto = new ClasseDto();
        dto.setId(classe.getId());
        dto.setNom(classe.getNom());
        dto.setNiveau(classe.getNiveau());
        return dto;
    }

    // Méthode pour convertir le DTO en entité Classe
    public Classe toEntity() {
        Classe classe = new Classe();
        classe.setId(this.id);
        classe.setNom(this.nom);
        classe.setNiveau(this.niveau);
        return classe;
    }
}
