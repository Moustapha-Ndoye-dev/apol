package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.EmploiDuTempsDto;
import com.spring.apol.services.EmploiDuTempsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emplois-du-temps") // Chemin de base pour toutes les requêtes de ce contrôleur
public class EmploiDuTempsController {

    private final EmploiDuTempsService emploiDuTempsService;

    public EmploiDuTempsController(EmploiDuTempsService emploiDuTempsService) {
        this.emploiDuTempsService = emploiDuTempsService;
    }

    // Créer un nouvel emploi du temps
    @PostMapping
    public ResponseEntity<EmploiDuTempsDto> createEmploiDuTemps(@RequestBody EmploiDuTempsDto emploiDuTempsDto) {
        EmploiDuTempsDto createdEmploiDuTemps = emploiDuTempsService.createEmploiDuTemps(emploiDuTempsDto);
        return new ResponseEntity<>(createdEmploiDuTemps, HttpStatus.CREATED); // Renvoie un statut 201
    }

    // Mettre à jour un emploi du temps existant
    @PutMapping("/{id}")
    public ResponseEntity<EmploiDuTempsDto> updateEmploiDuTemps(@PathVariable Long id, @RequestBody EmploiDuTempsDto emploiDuTempsDto) {
        EmploiDuTempsDto updatedEmploiDuTemps = emploiDuTempsService.updateEmploiDuTemps(id, emploiDuTempsDto);
        return new ResponseEntity<>(updatedEmploiDuTemps, HttpStatus.OK); // Renvoie un statut 200
    }

    // Récupérer un emploi du temps par ID
    @GetMapping("/{id}")
    public ResponseEntity<EmploiDuTempsDto> getEmploiDuTempsById(@PathVariable Long id) {
        EmploiDuTempsDto emploiDuTemps = emploiDuTempsService.getEmploiDuTempsById(id);
        return new ResponseEntity<>(emploiDuTemps, HttpStatus.OK); // Renvoie un statut 200
    }

    // Récupérer tous les emplois du temps
    @GetMapping
    public ResponseEntity<List<EmploiDuTempsDto>> getAllEmploisDuTemps() {
        List<EmploiDuTempsDto> emploisDuTemps = emploiDuTempsService.getAllEmploisDuTemps();
        return new ResponseEntity<>(emploisDuTemps, HttpStatus.OK); // Renvoie un statut 200
    }

    // Supprimer un emploi du temps par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmploiDuTemps(@PathVariable Long id) {
        emploiDuTempsService.deleteEmploiDuTemps(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Renvoie un statut 204
    }

    // Récupérer les emplois du temps par ID de classe
    @GetMapping("/classe/{classeId}")
    public ResponseEntity<List<EmploiDuTempsDto>> getEmploisDuTempsByClasseId(@PathVariable Long classeId) {
        List<EmploiDuTempsDto> emploisDuTemps = emploiDuTempsService.getEmploisDuTempsByClasseId(classeId);
        return new ResponseEntity<>(emploisDuTemps, HttpStatus.OK); // Renvoie un statut 200
    }

    // Récupérer les emplois du temps par ID de filière
    @GetMapping("/filiere/{filiereId}")
    public ResponseEntity<List<EmploiDuTempsDto>> getEmploisDuTempsByFiliereId(@PathVariable Long filiereId) {
        List<EmploiDuTempsDto> emploisDuTemps = emploiDuTempsService.getEmploisDuTempsByFiliereId(filiereId);
        return new ResponseEntity<>(emploisDuTemps, HttpStatus.OK); // Renvoie un statut 200
    }
}
