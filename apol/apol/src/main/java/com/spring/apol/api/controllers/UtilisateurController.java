package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.UtilisateurDto;
import com.spring.apol.data.enums.RoleUtilisateur;
import com.spring.apol.services.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "*")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public ResponseEntity<?> createUtilisateur(@Valid @RequestBody UtilisateurDto utilisateurDto,
                                               BindingResult bindingResult) {
        // Validation des champs
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            // Validation spécifique pour ETUDIANT et CHEF_CLASSE
            if ((utilisateurDto.getRole() == RoleUtilisateur.ETUDIANT ||
                    utilisateurDto.getRole() == RoleUtilisateur.CHEF_CLASSE) &&
                    (utilisateurDto.getClasseId() == null)) {
                return ResponseEntity.badRequest()
                        .body("La classe est obligatoires pour les étudiants et chefs de classe");
            }

            UtilisateurDto createdUser = utilisateurService.addUtilisateur(utilisateurDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création de l'utilisateur: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUtilisateur(@PathVariable Long id,
                                               @Valid @RequestBody UtilisateurDto utilisateurDto,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        return utilisateurService.updateUtilisateur(id, utilisateurDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<UtilisateurDto>> searchByNomComplet(@RequestParam String nom) {
        return ResponseEntity.ok(utilisateurService.searchUtilisateursByNomComplet(nom));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UtilisateurDto>> getByRole(@PathVariable RoleUtilisateur role) {
        return ResponseEntity.ok(utilisateurService.getUtilisateursByRole(role));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UtilisateurDto> getByEmail(@PathVariable String email) {
        return utilisateurService.getUtilisateurByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<UtilisateurDto> getByMatricule(@PathVariable String matricule) {
        return utilisateurService.getUtilisateurByMatricule(matricule)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}