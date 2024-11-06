package com.spring.apol.services;

import com.spring.apol.api.dto.UtilisateurDto;
import com.spring.apol.data.enums.RoleUtilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    UtilisateurDto addUtilisateur(UtilisateurDto utilisateurDto);

    Optional<UtilisateurDto> updateUtilisateur(Long id, UtilisateurDto utilisateurDto);

    List<UtilisateurDto> getAllUtilisateurs();

    Optional<UtilisateurDto> getUtilisateurById(Long id);

    void deleteUtilisateur(Long id);

    Optional<UtilisateurDto> getUtilisateurByEmail(String email);

    Optional<UtilisateurDto> getUtilisateurByMatricule(String matricule);

    List<UtilisateurDto> getUtilisateursByRole(RoleUtilisateur role);

    List<UtilisateurDto> searchUtilisateursByNomComplet(String nomComplet);
}
