package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.EmploiDuTempsDto;
import com.spring.apol.services.EmploiDuTempsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/emplois-du-temps")
public class EmploiDuTempsController {

    private final EmploiDuTempsService emploiDuTempsService;

    public EmploiDuTempsController(EmploiDuTempsService emploiDuTempsService) {
        this.emploiDuTempsService = emploiDuTempsService;
    }

    // Créer un nouvel emploi du temps
    @PostMapping
    public ResponseEntity<Map<String, Object>> createEmploiDuTemps(@RequestBody EmploiDuTempsDto emploiDuTempsDto) {
        try {
            EmploiDuTempsDto createdEmploiDuTemps = emploiDuTempsService.createEmploiDuTemps(emploiDuTempsDto);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Emploi du temps créé avec succès");
            response.put("emploiDuTemps", createdEmploiDuTemps);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // Renvoie un statut 201
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la création de l'emploi du temps");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle potential errors
        }
    }

    // Mettre à jour un emploi du temps existant
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateEmploiDuTemps(@PathVariable Long id, @RequestBody EmploiDuTempsDto emploiDuTempsDto) {
        try {
            Optional<EmploiDuTempsDto> existingEmploiDuTemps = Optional.ofNullable(emploiDuTempsService.updateEmploiDuTemps(id, emploiDuTempsDto));
            Map<String, Object> response = new HashMap<>();
            return existingEmploiDuTemps
                    .map(updatedEmploiDuTemps -> {
                        response.put("message", "Emploi du temps mis à jour avec succès");
                        response.put("emploiDuTemps", updatedEmploiDuTemps);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    })
                    .orElseGet(() -> {
                        response.put("error", "Emploi du temps non trouvé");
                        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // 404 if not found
                    });
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la mise à jour de l'emploi du temps");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
        }
    }

    // Récupérer un emploi du temps par ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEmploiDuTempsById(@PathVariable Long id) {
        try {
            Optional<EmploiDuTempsDto> emploiDuTemps = Optional.ofNullable(emploiDuTempsService.getEmploiDuTempsById(id));
            Map<String, Object> response = new HashMap<>();
            return emploiDuTemps
                    .map(dto -> {
                        response.put("emploiDuTemps", dto);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    })
                    .orElseGet(() -> {
                        response.put("error", "Emploi du temps non trouvé");
                        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // 404 if not found
                    });
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération de l'emploi du temps");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
        }
    }

    // Récupérer tous les emplois du temps
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEmploisDuTemps() {
        try {
            List<EmploiDuTempsDto> emploisDuTemps = emploiDuTempsService.getAllEmploisDuTemps();
            Map<String, Object> response = new HashMap<>();
            if (emploisDuTemps.isEmpty()) {
                response.put("message", "Aucun emploi du temps trouvé");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT); // 204 if no content
            } else {
                response.put("emploisDuTemps", emploisDuTemps);
                return new ResponseEntity<>(response, HttpStatus.OK); // 200 OK with list
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des emplois du temps");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Empty list in case of error
        }
    }

    // Supprimer un emploi du temps par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmploiDuTemps(@PathVariable Long id) {
        try {
            emploiDuTempsService.deleteEmploiDuTemps(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Emploi du temps supprimé avec succès");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT); // 204 No Content on successful deletion
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la suppression de l'emploi du temps");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
        }
    }

    // Récupérer les emplois du temps par ID de classe
    @GetMapping("/classe/{classeId}")
    public ResponseEntity<Map<String, Object>> getEmploisDuTempsByClasseId(@PathVariable Long classeId) {
        try {
            List<EmploiDuTempsDto> emploisDuTemps = emploiDuTempsService.getEmploisDuTempsByClasseId(classeId);
            Map<String, Object> response = new HashMap<>();
            if (emploisDuTemps.isEmpty()) {
                response.put("message", "Aucun emploi du temps trouvé pour cette classe");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT); // 204 if no content
            } else {
                response.put("emploisDuTemps", emploisDuTemps);
                return new ResponseEntity<>(response, HttpStatus.OK); // 200 OK with list
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des emplois du temps de la classe");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Return empty list in case of error
        }
    }

    // Récupérer les emplois du temps par ID de filière
    @GetMapping("/filiere/{filiereId}")
    public ResponseEntity<Map<String, Object>> getEmploisDuTempsByFiliereId(@PathVariable Long filiereId) {
        try {
            List<EmploiDuTempsDto> emploisDuTemps = emploiDuTempsService.getEmploisDuTempsByFiliereId(filiereId);
            Map<String, Object> response = new HashMap<>();
            if (emploisDuTemps.isEmpty()) {
                response.put("message", "Aucun emploi du temps trouvé pour cette filière");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT); // 204 if no content
            } else {
                response.put("emploisDuTemps", emploisDuTemps);
                return new ResponseEntity<>(response, HttpStatus.OK); // 200 OK with list
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des emplois du temps de la filière");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Return empty list in case of error
        }
    }
}
