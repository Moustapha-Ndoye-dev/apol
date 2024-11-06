package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.NoteEvaluationDto;
import com.spring.apol.services.NoteEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note-evaluations")
public class NoteEvaluationController {
    private final NoteEvaluationService noteEvaluationService;

    @Autowired
    public NoteEvaluationController(NoteEvaluationService noteEvaluationService) {
        this.noteEvaluationService = noteEvaluationService;
    }

    @PostMapping("/{professeurId}")
    public ResponseEntity<NoteEvaluationDto> createNoteEvaluation(
            @RequestBody NoteEvaluationDto noteEvaluationDto,
            @PathVariable Long professeurId) {
        NoteEvaluationDto createdNoteEvaluation = noteEvaluationService.save(noteEvaluationDto, professeurId);
        return ResponseEntity.ok(createdNoteEvaluation);
    }

    @PutMapping("/{id}/{professeurId}")
    public ResponseEntity<NoteEvaluationDto> updateNoteEvaluation(
            @PathVariable Long id,
            @RequestBody NoteEvaluationDto noteEvaluationDto,
            @PathVariable Long professeurId) {
        NoteEvaluationDto updatedNoteEvaluation = noteEvaluationService.update(id, noteEvaluationDto, professeurId);
        return ResponseEntity.ok(updatedNoteEvaluation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteEvaluation(@PathVariable Long id) {
        noteEvaluationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteEvaluationDto> getNoteEvaluationById(@PathVariable Long id) {
        NoteEvaluationDto noteEvaluationDto = noteEvaluationService.findById(id);
        return ResponseEntity.ok(noteEvaluationDto);
    }

    @GetMapping
    public ResponseEntity<List<NoteEvaluationDto>> getAllNoteEvaluations() {
        List<NoteEvaluationDto> noteEvaluations = noteEvaluationService.findAll();
        return ResponseEntity.ok(noteEvaluations);
    }

    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<NoteEvaluationDto>> getNoteEvaluationsByEtudiantId(@PathVariable Long etudiantId) {
        List<NoteEvaluationDto> noteEvaluations = noteEvaluationService.findByEtudiantId(etudiantId);
        return ResponseEntity.ok(noteEvaluations);
    }
}
