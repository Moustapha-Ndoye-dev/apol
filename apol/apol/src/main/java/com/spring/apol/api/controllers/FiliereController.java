package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.FiliereDto;
import com.spring.apol.services.FiliereService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filieres") // URL de base pour les requêtes
public class FiliereController {

    private final FiliereService filiereService;
    public FiliereController(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    // Endpoint pour créer une nouvelle filière
    @PostMapping
    public ResponseEntity<FiliereDto> createFiliere(@RequestBody FiliereDto filiereDto) {
        FiliereDto createdFiliere = filiereService.createFiliere(filiereDto);
        return ResponseEntity.ok(createdFiliere);
    }

    // Endpoint pour obtenir toutes les filières
    @GetMapping
    public ResponseEntity<List<FiliereDto>> getAllFilieres() {
        List<FiliereDto> filieres = filiereService.getAllFilieres();
        return ResponseEntity.ok(filieres);
    }

    // Endpoint pour obtenir une filière par ID
    @GetMapping("/{id}")
    public ResponseEntity<FiliereDto> getFiliereById(@PathVariable Long id) {
        Optional<FiliereDto> filiereDto = filiereService.getFiliereById(id);
        return filiereDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint pour supprimer une filière
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiliere(@PathVariable Long id) {
        filiereService.deleteFiliere(id);
        return ResponseEntity.noContent().build(); // Renvoie un statut 204 No Content
    }
}
