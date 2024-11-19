package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.ModuleDto;
import com.spring.apol.data.models.Module;
import com.spring.apol.services.ModuleService;
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
@RequestMapping("/api/modules")
public class ModuleController {

    private final ModuleService moduleService;

    @Autowired
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    // Endpoint pour obtenir tous les modules
    @GetMapping
    public ResponseEntity<String> getAllModules() {
        try {
            List<Module> modules = moduleService.findAll();
            return ok(modules.toString());
        } catch (RuntimeException e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des modules: " + e.getMessage());
        }
    }

    // Endpoint pour obtenir un module par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getModuleById(@PathVariable Long id) {
        try {
            Optional<Module> module = moduleService.findById(id);
            if (module.isPresent()) {
                return ok(module.get());
            } else {
                return status(HttpStatus.NOT_FOUND)
                        .body("Module non trouvé avec l'ID: " + id);
            }
        } catch (RuntimeException e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération du module avec l'ID " + id + ": " + e.getMessage());
        }
    }

    // Endpoint pour créer un module
    @PostMapping
    public ResponseEntity<?> createModule(@RequestBody ModuleDto moduleDto, BindingResult bindingResult) {
        // Validation des entrées
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return badRequest().body(errors);
        }

        try {
            Module module = moduleService.save(moduleDto);
            return status(HttpStatus.CREATED).body(module);
        } catch (RuntimeException e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création du module: " + e.getMessage());
        }
    }

    // Endpoint pour supprimer un module
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable Long id) {
        try {
            moduleService.deleteById(id);
            return noContent().build();
        } catch (RuntimeException e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du module avec l'ID " + id + ": " + e.getMessage());
        }
    }

    // Endpoint pour rechercher des modules par nom
    @GetMapping("/search")
    public ResponseEntity<?> searchModules(@RequestParam String nom) {
        try {
            List<Module> modules = moduleService.findByNom(nom);
            if (modules.isEmpty()) {
                return status(HttpStatus.NOT_FOUND)
                        .body("Aucun module trouvé avec le nom: " + nom);
            }
            return ok(modules);
        } catch (RuntimeException e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la recherche des modules: " + e.getMessage());
        }
    }
}
