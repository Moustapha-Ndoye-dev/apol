package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.BulletinDto;
import com.spring.apol.services.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bulletins")
public class BulletinController {

    private final BulletinService bulletinService;

    @Autowired
    public BulletinController(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    // Créer un nouveau bulletin
    @PostMapping
    public ResponseEntity<Map<String, Object>> createBulletin(@RequestBody BulletinDto bulletinDto) {
        try {
            BulletinDto createdBulletin = bulletinService.createBulletin(bulletinDto);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Bulletin créé avec succès");
            response.put("bulletin", createdBulletin);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la création du bulletin");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtenir un bulletin par ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBulletinById(@PathVariable Long id) {
        try {
            BulletinDto bulletinDto = bulletinService.getBulletinById(id);
            Map<String, Object> response = new HashMap<>();
            if (bulletinDto != null) {
                response.put("bulletin", bulletinDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("error", "Bulletin non trouvé");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération du bulletin");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Mettre à jour un bulletin
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBulletin(@PathVariable Long id, @RequestBody BulletinDto bulletinDto) {
        try {
            BulletinDto updatedBulletin = bulletinService.updateBulletin(id, bulletinDto);
            Map<String, Object> response = new HashMap<>();
            if (updatedBulletin != null) {
                response.put("message", "Bulletin mis à jour avec succès");
                response.put("bulletin", updatedBulletin);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("error", "Bulletin non trouvé");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la mise à jour du bulletin");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Supprimer un bulletin
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBulletin(@PathVariable Long id) {
        try {
            boolean deleted = bulletinService.deleteBulletin(id);
            Map<String, Object> response = new HashMap<>();
            if (deleted) {
                response.put("message", "Bulletin supprimé avec succès");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.put("error", "Bulletin non trouvé");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la suppression du bulletin");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtenir tous les bulletins d'un étudiant
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<Map<String, Object>> getBulletinsByEtudiantId(@PathVariable Long etudiantId) {
        try {
            List<BulletinDto> bulletins = bulletinService.getBulletinsByEtudiantId(etudiantId);
            Map<String, Object> response = new HashMap<>();
            response.put("bulletins", bulletins);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des bulletins de l'étudiant");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtenir tous les bulletins d'une classe
    @GetMapping("/classe/{classeId}")
    public ResponseEntity<Map<String, Object>> getBulletinsByClasseId(@PathVariable Long classeId) {
        try {
            List<BulletinDto> bulletins = bulletinService.getBulletinsByClasseId(classeId);
            Map<String, Object> response = new HashMap<>();
            response.put("bulletins", bulletins);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des bulletins de la classe");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtenir tous les bulletins d'une filière
    @GetMapping("/filiere/{filiereId}")
    public ResponseEntity<Map<String, Object>> getBulletinsByFiliereId(@PathVariable Long filiereId) {
        try {
            List<BulletinDto> bulletins = bulletinService.getBulletinsByFiliereId(filiereId);
            Map<String, Object> response = new HashMap<>();
            response.put("bulletins", bulletins);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des bulletins de la filière");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
