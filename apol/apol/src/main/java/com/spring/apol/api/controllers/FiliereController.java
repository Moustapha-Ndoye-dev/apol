package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.FiliereDto;
import com.spring.apol.services.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/filieres")
public class FiliereController {

    private final FiliereService filiereService;

    @Autowired
    public FiliereController(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    // Endpoint pour créer une nouvelle filière
    @PostMapping
    public ResponseEntity<?> createFiliere(@RequestBody FiliereDto filiereDto, BindingResult bindingResult) {
        // Validation des entrées
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return badRequest().body(errors); // Return 400 Bad Request with error details
        }

        try {
            FiliereDto createdFiliere = filiereService.createFiliere(filiereDto);
            return status(HttpStatus.CREATED).body(createdFiliere); // Return 201 Created with the created object
        } catch (RuntimeException e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new FiliereDto()); // Return an empty FiliereDto or error response
        }
    }

    // Endpoint pour obtenir toutes les filières
    @GetMapping
    public ResponseEntity<?> getAllFilieres() {
        try {
            List<FiliereDto> filieres = filiereService.getAllFilieres();
            if (filieres.isEmpty()) {
                return status(HttpStatus.NOT_FOUND).body(new FiliereDto()); // Return 404 with an empty FiliereDto
            }
            return ok(filieres); // Return 200 OK with the list of filieres
        } catch (RuntimeException e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new FiliereDto()); // Return an empty FiliereDto or error response
        }
    }

    // Endpoint pour obtenir une filière par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getFiliereById(@PathVariable Long id) {
        try {
            Optional<FiliereDto> filiereDto = filiereService.getFiliereById(id);
            return filiereDto.map(ResponseEntity::ok) // Return 200 OK if found
                    .orElseGet(() -> status(HttpStatus.NOT_FOUND)
                            .body(new FiliereDto())); // Return 404 with an empty FiliereDto if not found
        } catch (RuntimeException e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new FiliereDto()); // Return 500 Internal Server Error
        }
    }

    // Endpoint pour supprimer une filière
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFiliere(@PathVariable Long id) {
        try {
            filiereService.deleteFiliere(id);
            return noContent().build(); // Return 204 No Content if deleted successfully
        } catch (RuntimeException e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new FiliereDto()); // Return an empty FiliereDto or error response
        }
    }
}
