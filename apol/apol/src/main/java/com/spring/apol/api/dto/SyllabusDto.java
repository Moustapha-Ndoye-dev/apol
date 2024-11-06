package com.spring.apol.api.dto;

import com.spring.apol.data.models.Syllabus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SyllabusDto {
    private Long id;
    private String titre;
    private String objectifs;
    private LocalDate dateCreation;
    private int dureeEnSemaines;
    private String cheminDocument;
    private Long moduleId; // ID du module associé
    private Long professeurId; // ID du professeur associé

    // Convertir l'entité Syllabus en DTO
    public static SyllabusDto fromEntity(Syllabus syllabus) {
        SyllabusDto dto = new SyllabusDto();
        dto.setId(syllabus.getId());
        dto.setTitre(syllabus.getTitre());
        dto.setObjectifs(syllabus.getObjectifs());
        dto.setDateCreation(syllabus.getDateCreation());
        dto.setDureeEnSemaines(syllabus.getDureeEnSemaines());
        dto.setCheminDocument(syllabus.getCheminDocument());

        if (syllabus.getModule() != null) {
            dto.setModuleId(syllabus.getModule().getId());
        }

        if (syllabus.getProfesseur() != null) {
            dto.setProfesseurId(syllabus.getProfesseur().getId());
        }

        return dto;
    }

    // Convertir le DTO en entité Syllabus
    public Syllabus toEntity() {
        Syllabus syllabus = new Syllabus();
        syllabus.setId(this.id);
        syllabus.setTitre(this.titre);
        syllabus.setObjectifs(this.objectifs);
        syllabus.setDateCreation(this.dateCreation);
        syllabus.setDureeEnSemaines(this.dureeEnSemaines);
        syllabus.setCheminDocument(this.cheminDocument);

        return syllabus;
    }
}