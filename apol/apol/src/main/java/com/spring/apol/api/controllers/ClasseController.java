package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.ClasseDto;
import com.spring.apol.services.ClasseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/classes")
public class ClasseController {

    private final ClasseService classeService;
    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    // Créer une nouvelle classe
    @PostMapping("/add")
    public ResponseEntity<ClasseDto> createClasse(@RequestBody ClasseDto classeDto) {
        ClasseDto createdClasse = classeService.createClasse(classeDto);
        return new ResponseEntity<>(createdClasse, HttpStatus.CREATED);
    }

    // Mettre à jour une classe existante
    @PutMapping("/{id}")
    public ResponseEntity<ClasseDto> updateClasse(@PathVariable Long id, @RequestBody ClasseDto classeDto) {
        ClasseDto updatedClasse = classeService.updateClasse(id, classeDto);
        return new ResponseEntity<>(updatedClasse, HttpStatus.OK);
    }

    // Récupérer une classe par ID
    @GetMapping("/{id}")
    public ResponseEntity<ClasseDto> getClasseById(@PathVariable Long id) {
        ClasseDto classeDto = classeService.getClasseById(id);
        return new ResponseEntity<>(classeDto, HttpStatus.OK);
    }

    // Récupérer toutes les classes
    @GetMapping
    public ResponseEntity<List<ClasseDto>> getAllClasses() {
        List<ClasseDto> classes = classeService.getAllClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    // Supprimer une classe par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable Long id) {
        classeService.deleteClasse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Récupérer des classes par ID de filière
    @GetMapping("/filiere/{filiereId}")
    public ResponseEntity<List<ClasseDto>> getClassesByFiliereId(@PathVariable Long filiereId) {
        List<ClasseDto> classes = classeService.getClassesByFiliereId(filiereId);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }
}
