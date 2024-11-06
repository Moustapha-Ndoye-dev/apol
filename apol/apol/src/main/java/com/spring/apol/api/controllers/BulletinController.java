package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.BulletinDto;
import com.spring.apol.services.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<BulletinDto> createBulletin(@RequestBody BulletinDto bulletinDto) {
        BulletinDto createdBulletin = bulletinService.createBulletin(bulletinDto);
        return new ResponseEntity<>(createdBulletin, HttpStatus.CREATED);
    }

    // Obtenir un bulletin par ID
    @GetMapping("/{id}")
    public ResponseEntity<BulletinDto> getBulletinById(@PathVariable Long id) {
        BulletinDto bulletinDto = bulletinService.getBulletinById(id);
        return ResponseEntity.ok(bulletinDto);
    }

    // Mettre à jour un bulletin
    @PutMapping("/{id}")
    public ResponseEntity<BulletinDto> updateBulletin(@PathVariable Long id, @RequestBody BulletinDto bulletinDto) {
        BulletinDto updatedBulletin = bulletinService.updateBulletin(id, bulletinDto);
        return ResponseEntity.ok(updatedBulletin);
    }

    // Supprimer un bulletin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBulletin(@PathVariable Long id) {
        bulletinService.deleteBulletin(id);
        return ResponseEntity.noContent().build();
    }

    // Obtenir tous les bulletins d'un étudiant
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<BulletinDto>> getBulletinsByEtudiantId(@PathVariable Long etudiantId) {
        List<BulletinDto> bulletins = bulletinService.getBulletinsByEtudiantId(etudiantId);
        return ResponseEntity.ok(bulletins);
    }

    // Obtenir tous les bulletins d'une classe
    @GetMapping("/classe/{classeId}")
    public ResponseEntity<List<BulletinDto>> getBulletinsByClasseId(@PathVariable Long classeId) {
        List<BulletinDto> bulletins = bulletinService.getBulletinsByClasseId(classeId);
        return ResponseEntity.ok(bulletins);
    }

    // Obtenir tous les bulletins d'une filière
    @GetMapping("/filiere/{filiereId}")
    public ResponseEntity<List<BulletinDto>> getBulletinsByFiliereId(@PathVariable Long filiereId) {
        List<BulletinDto> bulletins = bulletinService.getBulletinsByFiliereId(filiereId);
        return ResponseEntity.ok(bulletins);
    }
}
