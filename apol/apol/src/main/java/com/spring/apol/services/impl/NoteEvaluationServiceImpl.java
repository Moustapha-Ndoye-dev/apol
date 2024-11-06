package com.spring.apol.services.impl;

import com.spring.apol.api.dto.NoteEvaluationDto;
import com.spring.apol.data.models.NoteEvaluation;
import com.spring.apol.data.models.Utilisateur;
import com.spring.apol.data.repositories.NoteEvaluationRepository;
import com.spring.apol.services.NoteEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteEvaluationServiceImpl implements NoteEvaluationService {
    private final NoteEvaluationRepository noteEvaluationRepository;

    @Autowired
    public NoteEvaluationServiceImpl(NoteEvaluationRepository noteEvaluationRepository) {
        this.noteEvaluationRepository = noteEvaluationRepository;
    }

    @Override
    public NoteEvaluationDto save(NoteEvaluationDto noteEvaluationDto, Long professeurId) {
        NoteEvaluation noteEvaluation = noteEvaluationDto.toEntity();

        Utilisateur professeur = new Utilisateur();
        professeur.setId(professeurId);
        noteEvaluation.setProfesseur(professeur);

        noteEvaluation = noteEvaluationRepository.save(noteEvaluation);
        return NoteEvaluationDto.fromEntity(noteEvaluation);
    }

    @Override
    public NoteEvaluationDto update(Long id, NoteEvaluationDto noteEvaluationDto, Long professeurId) {
        NoteEvaluation existingNote = noteEvaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NoteEvaluation not found"));

        if (!existingNote.getProfesseur().getId().equals(professeurId)) {
            throw new RuntimeException("Vous ne pouvez pas modifier une note que vous n'avez pas créée");
        }

        existingNote.setNote(noteEvaluationDto.getNote());
        existingNote.setCommentaires(noteEvaluationDto.getCommentaires());
        existingNote.setDateSubmission(noteEvaluationDto.getDateSubmission());

        noteEvaluationRepository.save(existingNote);

        return NoteEvaluationDto.fromEntity(existingNote);
    }

    @Override
    public void delete(Long id) {
        noteEvaluationRepository.deleteById(id);
    }

    @Override
    public NoteEvaluationDto findById(Long id) {
        NoteEvaluation noteEvaluation = noteEvaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NoteEvaluation not found"));
        return NoteEvaluationDto.fromEntity(noteEvaluation);
    }

    @Override
    public List<NoteEvaluationDto> findAll() {
        return noteEvaluationRepository.findAll()
                .stream()
                .map(NoteEvaluationDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteEvaluationDto> findByEtudiantId(Long etudiantId) {
        return noteEvaluationRepository.findByEtudiantId(etudiantId)
                .stream()
                .map(NoteEvaluationDto::fromEntity)
                .collect(Collectors.toList());
    }
}
