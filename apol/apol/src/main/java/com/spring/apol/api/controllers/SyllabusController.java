package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.SyllabusDto;
import com.spring.apol.services.SyllabusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/syllabi") // URL de base pour les endpoints de syllabus
public class SyllabusController {

    private final SyllabusService syllabusService;
    public SyllabusController(SyllabusService syllabusService) {
        this.syllabusService = syllabusService;
    }

    // Endpoint pour créer un syllabus
    @PostMapping
    public ResponseEntity<SyllabusDto> createSyllabus(@RequestBody SyllabusDto syllabusDto) {
        SyllabusDto createdSyllabus = syllabusService.createSyllabus(syllabusDto);
        return ResponseEntity.ok(createdSyllabus);
    }

    // Endpoint pour obtenir un syllabus par ID
    @GetMapping("/{id}")
    public ResponseEntity<SyllabusDto> getSyllabusById(@PathVariable Long id) {
        SyllabusDto syllabusDto = syllabusService.getSyllabusById(id);
        return ResponseEntity.ok(syllabusDto);
    }

    // Endpoint pour obtenir tous les syllabi
    @GetMapping
    public ResponseEntity<List<SyllabusDto>> getAllSyllabi() {
        List<SyllabusDto> syllabi = syllabusService.getAllSyllabi();
        return ResponseEntity.ok(syllabi);
    }

    // Endpoint pour mettre à jour un syllabus
    @PutMapping("/{id}")
    public ResponseEntity<SyllabusDto> updateSyllabus(@PathVariable Long id, @RequestBody SyllabusDto syllabusDto) {
        SyllabusDto updatedSyllabus = syllabusService.updateSyllabus(id, syllabusDto);
        return ResponseEntity.ok(updatedSyllabus);
    }

    // Endpoint pour supprimer un syllabus
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSyllabus(@PathVariable Long id) {
        syllabusService.deleteSyllabus(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint pour obtenir les syllabi par ID de professeur
    @GetMapping("/professeur/{professeurId}")
    public ResponseEntity<List<SyllabusDto>> getSyllabiByProfesseurId(@PathVariable Long professeurId) {
        List<SyllabusDto> syllabi = syllabusService.getSyllabiByProfesseurId(professeurId);
        return ResponseEntity.ok(syllabi);
    }

    // Endpoint pour rechercher des syllabi par titre
    @GetMapping("/search")
    public ResponseEntity<List<SyllabusDto>> searchSyllabiByTitle(@RequestParam String titre) {
        List<SyllabusDto> syllabi = syllabusService.searchSyllabiByTitle(titre);
        return ResponseEntity.ok(syllabi);
    }
}
