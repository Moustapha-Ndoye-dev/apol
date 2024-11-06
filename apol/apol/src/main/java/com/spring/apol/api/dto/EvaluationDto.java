package com.spring.apol.api.dto;

import com.spring.apol.data.models.Classe;
import com.spring.apol.data.models.Evaluation;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class EvaluationDto {
    private Long id;
    private LocalDate date; // Date de l'évaluation
    private String type; // Type de l'évaluation (Examen, Devoir, etc.)
    private Long moduleId; // ID du module associé
    private List<Long> classesIds; // Liste des ID des classes associées
    private List<NoteEvaluationDto> notes; // Liste des notes d'évaluation

    public Evaluation toEntity() {
        Evaluation evaluation = new Evaluation();
        evaluation.setId(this.id);
        evaluation.setDate(this.date);
        evaluation.setType(this.type);

        // Si vous souhaitez récupérer le module associé, décommentez la ligne suivante
        // evaluation.setModule(new Module(this.moduleId)); // Uncomment if Module entity is needed

        return evaluation;
    }

    public static EvaluationDto fromEntity(Evaluation evaluation) {
        EvaluationDto dto = new EvaluationDto();
        dto.setId(evaluation.getId());
        dto.setDate(evaluation.getDate());
        dto.setType(evaluation.getType());

        // Récupérer l'ID du module associé
        if (evaluation.getModule() != null) {
            dto.setModuleId(evaluation.getModule().getId());
        }

        // Récupérer les IDs des classes associées
        dto.setClassesIds(evaluation.getClasses().stream()
                .map(Classe::getId)
                .collect(Collectors.toList()));

        // Récupérer les notes d'évaluation
        dto.setNotes(evaluation.getNotes().stream()
                .map(NoteEvaluationDto::fromEntity)
                .collect(Collectors.toList()));

        return dto;
    }
}
