package com.spring.apol.api.dto;

import com.spring.apol.data.models.NoteEvaluation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteEvaluationDto {
    private Long id;
    private Double note;
    private String commentaires;
    private LocalDateTime dateSubmission;
    private Long etudiantId; // ID de l'étudiant
    private Long professeurId; // ID du professeur

    public NoteEvaluation toEntity() {
        NoteEvaluation noteEvaluation = new NoteEvaluation();
        noteEvaluation.setId(this.id);
        noteEvaluation.setNote(this.note);
        noteEvaluation.setCommentaires(this.commentaires);
        noteEvaluation.setDateSubmission(this.dateSubmission);
        return noteEvaluation;
    }

    public static NoteEvaluationDto fromEntity(NoteEvaluation noteEvaluation) {
        NoteEvaluationDto dto = new NoteEvaluationDto();
        dto.setId(noteEvaluation.getId());
        dto.setNote(noteEvaluation.getNote());
        dto.setCommentaires(noteEvaluation.getCommentaires());
        dto.setDateSubmission(noteEvaluation.getDateSubmission());
        if (noteEvaluation.getEtudiant() != null) {
            dto.setEtudiantId(noteEvaluation.getEtudiant().getId());
        }
        if (noteEvaluation.getProfesseur() != null) {
            dto.setProfesseurId(noteEvaluation.getProfesseur().getId());
        }
        return dto;
    }
}
