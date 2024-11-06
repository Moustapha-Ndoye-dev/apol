package com.spring.apol.api.dto;

import com.spring.apol.data.enums.RoleUtilisateur;
import com.spring.apol.data.models.Classe;
import com.spring.apol.data.models.Filiere;
import com.spring.apol.data.models.Utilisateur;
import lombok.Data;

@Data
public class UtilisateurDto {
    private Long id;
    private String nomComplet;
    private String email;
    private String password;
    private String moisNaissance;
    private int anneeNaissance;
    private String telephone;
    private String matricule;
    private RoleUtilisateur role;
    private Long classeId;
    private Long filiereId;

    // Convertir l'entité Utilisateur en DTO
    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        UtilisateurDto dto = new UtilisateurDto();
        dto.setId(utilisateur.getId());
        dto.setNomComplet(utilisateur.getNomComplet());
        dto.setEmail(utilisateur.getEmail());
        dto.setPassword(utilisateur.getPassword());
        dto.setMoisNaissance(utilisateur.getMoisNaissance());
        dto.setAnneeNaissance(utilisateur.getAnneeNaissance());
        dto.setTelephone(utilisateur.getTelephone());
        dto.setMatricule(utilisateur.getMatricule());
        dto.setRole(utilisateur.getRole());

        if (utilisateur.getClasse() != null) {
            dto.setClasseId(utilisateur.getClasse().getId());
        }

        if (utilisateur.getFiliereId() != null) {
            dto.setFiliereId(utilisateur.getFiliereId().getId());
        }

        return dto;
    }

    // Convertir le DTO en entité Utilisateur
    public Utilisateur toEntity() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(this.id);
        utilisateur.setNomComplet(this.nomComplet);
        utilisateur.setEmail(this.email);
        utilisateur.setPassword(this.password);
        utilisateur.setMoisNaissance(this.moisNaissance);
        utilisateur.setAnneeNaissance(this.anneeNaissance);
        utilisateur.setTelephone(this.telephone);
        utilisateur.setMatricule(this.matricule);
        utilisateur.setRole(this.role);

        // Set classe and filiere only if the role is ETUDIANT or CHEF_DE_CLASSE
        if (this.role == RoleUtilisateur.ETUDIANT || this.role == RoleUtilisateur.CHEF_CLASSE) {
            if (this.classeId != null) {
                Classe classe = new Classe();
                classe.setId(this.classeId);
                utilisateur.setClasse(classe);
            }

            if (this.filiereId != null) {
                Filiere filiere = new Filiere();
                filiere.setId(this.filiereId);
                utilisateur.setFiliereId(filiere);
            }
        } else {
            // Set classe and filiere to null for roles other than ETUDIANT or CHEF_DE_CLASSE
            utilisateur.setClasse(null);
            utilisateur.setFiliereId(null);
        }

        return utilisateur;
    }
}
