package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.ClasseDto;
import com.spring.apol.services.ClasseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> createClasse(@RequestBody ClasseDto classeDto) {
        try {
            ClasseDto createdClasse = classeService.createClasse(classeDto);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Classe créée avec succès");
            response.put("classe", createdClasse);

            return new ResponseEntity<>(response, HttpStatus.CREATED); // Return status 201
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la création de la classe");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle unexpected errors
        }
    }

    // Mettre à jour une classe existante
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateClasse(@PathVariable Long id, @RequestBody ClasseDto classeDto) {
        try {
            ClasseDto updatedClasse = classeService.updateClasse(id, classeDto);
            Map<String, Object> response = new HashMap<>();
            if (updatedClasse != null) {
                response.put("message", "Classe mise à jour avec succès");
                response.put("classe", updatedClasse);
                return new ResponseEntity<>(response, HttpStatus.OK); // Return status 200
            } else {
                response.put("error", "Classe non trouvée");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Return 404 if class not found
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la mise à jour de la classe");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle unexpected errors
        }
    }

    // Récupérer une classe par ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getClasseById(@PathVariable Long id) {
        try {
            ClasseDto classeDto = classeService.getClasseById(id);
            Map<String, Object> response = new HashMap<>();
            if (classeDto != null) {
                response.put("classe", classeDto);
                return new ResponseEntity<>(response, HttpStatus.OK); // Return status 200
            } else {
                response.put("error", "Classe non trouvée");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Return 404 if class not found
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération de la classe");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle unexpected errors
        }
    }

    // Récupérer toutes les classes
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllClasses() {
        try {
            List<ClasseDto> classes = classeService.getAllClasses();
            Map<String, Object> response = new HashMap<>();
            response.put("classes", classes);
            return new ResponseEntity<>(response, HttpStatus.OK); // Return status 200
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des classes");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle unexpected errors
        }
    }

    // Supprimer une classe par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteClasse(@PathVariable Long id) {
        try {
            boolean deleted = classeService.deleteClasse(id);
            Map<String, Object> response = new HashMap<>();
            if (deleted) {
                response.put("message", "Classe supprimée avec succès");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT); // Return 204 if deleted successfully
            } else {
                response.put("error", "Classe non trouvée");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Return 404 if class not found
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la suppression de la classe");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle unexpected errors
        }
    }
}
