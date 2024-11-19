package com.spring.apol.api.dto;

import com.spring.apol.data.enums.RoleUtilisateur;
import com.spring.apol.data.models.Classe;
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
    private int jourDeNaissance;
    private String telephone;
    private String matricule;
    private RoleUtilisateur role;
    private Long classeId;
    private String photo;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        UtilisateurDto dto = new UtilisateurDto();
        dto.setId(utilisateur.getId());
        dto.setNomComplet(utilisateur.getNomComplet());
        dto.setEmail(utilisateur.getEmail());
        dto.setPassword(utilisateur.getPassword());
        dto.setMoisNaissance(utilisateur.getMoisNaissance());
        dto.setAnneeNaissance(utilisateur.getAnneeNaissance());
        dto.setJourDeNaissance(utilisateur.getJourDeNaissance());
        dto.setTelephone(utilisateur.getTelephone());
        dto.setMatricule(utilisateur.getMatricule());
        dto.setRole(utilisateur.getRole());
        dto.setPhoto(utilisateur.getPhoto());

        if (utilisateur.getClasse() != null) {
            dto.setClasseId(utilisateur.getClasse().getId());
        }

        return dto;
    }

    public Utilisateur toEntity() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(this.id);
        utilisateur.setNomComplet(this.nomComplet);
        utilisateur.setEmail(this.email);
        utilisateur.setPassword(this.password);
        utilisateur.setMoisNaissance(this.moisNaissance);
        utilisateur.setAnneeNaissance(this.anneeNaissance);
        utilisateur.setJourDeNaissance(this.jourDeNaissance);
        utilisateur.setTelephone(this.telephone);
        utilisateur.setMatricule(this.matricule);
        utilisateur.setRole(this.role);
        utilisateur.setPhoto(this.photo);

        if (this.role == RoleUtilisateur.ETUDIANT || this.role == RoleUtilisateur.CHEF_CLASSE) {
            if (this.classeId != null) {
                Classe classe = new Classe();
                classe.setId(this.classeId);
                utilisateur.setClasse(classe);
            }

        }
        return utilisateur;
    }
}