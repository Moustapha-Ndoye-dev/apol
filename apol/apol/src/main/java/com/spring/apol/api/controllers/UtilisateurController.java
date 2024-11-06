package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.UtilisateurDto;
import com.spring.apol.data.enums.RoleUtilisateur;
import com.spring.apol.services.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Ajouter un utilisateur
    @PostMapping("/add")
    public ResponseEntity<UtilisateurDto> addUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        UtilisateurDto createdUser = utilisateurService.addUtilisateur(utilisateurDto);
        return ResponseEntity.ok(createdUser);
    }

    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDto utilisateurDto) {
        Optional<UtilisateurDto> updatedUser = utilisateurService.updateUtilisateur(id, utilisateurDto);
        return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtenir tous les utilisateurs
    @GetMapping
    public List<UtilisateurDto> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    // Obtenir un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    // Rechercher un utilisateur par email
    @GetMapping("/search/email/{email}")
    public ResponseEntity<UtilisateurDto> getUtilisateurByEmail(@PathVariable String email) {
        return utilisateurService.getUtilisateurByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Rechercher un utilisateur par matricule
    @GetMapping("/search/matricule/{matricule}")
    public ResponseEntity<UtilisateurDto> getUtilisateurByMatricule(@PathVariable String matricule) {
        return utilisateurService.getUtilisateurByMatricule(matricule)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Rechercher tous les utilisateurs par rôle
    @GetMapping("/search/role/{role}")
    public List<UtilisateurDto> getUtilisateursByRole(@PathVariable RoleUtilisateur role) {
        return utilisateurService.getUtilisateursByRole(role);
    }

    // Rechercher des utilisateurs par nom complet
    @GetMapping("/search/nom/{nomComplet}")
    public List<UtilisateurDto> searchUtilisateursByNomComplet(@PathVariable String nomComplet) {
        return utilisateurService.searchUtilisateursByNomComplet(nomComplet);
    }
}
