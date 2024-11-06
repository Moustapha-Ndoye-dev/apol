package com.spring.apol.api.dto;

import com.spring.apol.data.models.Bulletin;
import com.spring.apol.data.models.NoteEvaluation;
import lombok.Data;

import java.util.List;

@Data
public class BulletinDto {
    private Long id; // Identifiant unique du bulletin
    private String nomEtablissement; // Nom de l'établissement
    private String adresseEtablissement; // Adresse de l'établissement
    private String emailEtablissement; // Email de l'établissement
    private String telephoneEtablissement; // Numéro de téléphone de l'établissement
    private String appreciation; // Appréciation du bulletin
    private Long etudiantId; // ID de l'étudiant associé
    private Long classeId; // ID de la classe associée
    private Long filiereId; // ID de la filière associée
    private List<Long> notesIds; // Liste des IDs des notes d'évaluation associées

    // Constructeur par défaut
    public BulletinDto() {}

    // Constructeur avec paramètres
    public BulletinDto(Long id, String nomEtablissement, String adresseEtablissement,
                       String emailEtablissement, String telephoneEtablissement,
                       String appreciation, Long etudiantId, Long classeId, Long filiereId,
                       List<Long> notesIds) {
        this.id = id;
        this.nomEtablissement = nomEtablissement;
        this.adresseEtablissement = adresseEtablissement;
        this.emailEtablissement = emailEtablissement;
        this.telephoneEtablissement = telephoneEtablissement;
        this.appreciation = appreciation;
        this.etudiantId = etudiantId;
        this.classeId = classeId;
        this.filiereId = filiereId;
        this.notesIds = notesIds;
    }

    // Méthode pour créer un DTO à partir d'une entité Bulletin
    public static BulletinDto fromEntity(Bulletin bulletin) {
        BulletinDto dto = new BulletinDto();
        dto.setId(bulletin.getId());
        dto.setNomEtablissement(bulletin.getNomEtablissement());
        dto.setAdresseEtablissement(bulletin.getAdresseEtablissement());
        dto.setEmailEtablissement(bulletin.getEmailEtablissement());
        dto.setTelephoneEtablissement(bulletin.getTelephoneEtablissement());
        dto.setAppreciation(bulletin.getAppreciation());
        dto.setEtudiantId(bulletin.getEtudiant().getId());
        dto.setClasseId(bulletin.getClasse() != null ? bulletin.getClasse().getId() : null);
        dto.setFiliereId(bulletin.getFiliere() != null ? bulletin.getFiliere().getId() : null);
        dto.setNotesIds(bulletin.getNotes().stream().map(NoteEvaluation::getId).toList());
        return dto;
    }

    // Méthode pour convertir le DTO en entité Bulletin
    public Bulletin toEntity() {
        Bulletin bulletin = new Bulletin();
        bulletin.setId(this.id);
        bulletin.setNomEtablissement(this.nomEtablissement);
        bulletin.setAdresseEtablissement(this.adresseEtablissement);
        bulletin.setEmailEtablissement(this.emailEtablissement);
        bulletin.setTelephoneEtablissement(this.telephoneEtablissement);
        bulletin.setAppreciation(this.appreciation);
        return bulletin;
    }
}
