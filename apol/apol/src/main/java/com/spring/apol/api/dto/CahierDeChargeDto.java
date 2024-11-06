package com.spring.apol.api.dto;

import com.spring.apol.data.models.CahierDeCharge;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CahierDeChargeDto {
    private Long id; // Identifiant unique du cahier de charge
    private String texte; // Contenu du cahier de charge
    private LocalDate date; // Date du cahier de charge
    private Long professeurId; // ID du professeur associé
    private Long chefClasseId; // ID du chef de classe associé

    // Constructeur par défaut
    public CahierDeChargeDto() {}

    // Constructeur avec paramètres
    public CahierDeChargeDto(Long id, String texte, LocalDate date, Long professeurId, Long chefClasseId) {
        this.id = id;
        this.texte = texte;
        this.date = date;
        this.professeurId = professeurId;
        this.chefClasseId = chefClasseId;
    }

    // Méthode pour créer un DTO à partir d'une entité CahierDeCharge
    public static CahierDeChargeDto fromEntity(CahierDeCharge cahierDeCharge) {
        CahierDeChargeDto dto = new CahierDeChargeDto();
        dto.setId(cahierDeCharge.getId());
        dto.setTexte(cahierDeCharge.getTexte());
        dto.setDate(cahierDeCharge.getDate());
        dto.setProfesseurId(cahierDeCharge.getProfesseur() != null ? cahierDeCharge.getProfesseur().getId() : null);
        dto.setChefClasseId(cahierDeCharge.getChefClasse() != null ? cahierDeCharge.getChefClasse().getId() : null);
        return dto;
    }

    // Méthode pour convertir le DTO en entité CahierDeCharge
    public CahierDeCharge toEntity() {
        CahierDeCharge cahierDeCharge = new CahierDeCharge();
        cahierDeCharge.setId(this.id);
        cahierDeCharge.setTexte(this.texte);
        cahierDeCharge.setDate(this.date);
        return cahierDeCharge;
    }
}
