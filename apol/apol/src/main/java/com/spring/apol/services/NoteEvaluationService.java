package com.spring.apol.services;

import com.spring.apol.api.dto.NoteEvaluationDto;

import java.util.List;

public interface NoteEvaluationService {

    NoteEvaluationDto save(NoteEvaluationDto noteEvaluationDto, Long professeurId);

    NoteEvaluationDto update(Long id, NoteEvaluationDto noteEvaluationDto, Long professeurId);

    void delete(Long id);

    NoteEvaluationDto findById(Long id);

    List<NoteEvaluationDto> findAll();

    List<NoteEvaluationDto> findByEtudiantId(Long etudiantId);
}
