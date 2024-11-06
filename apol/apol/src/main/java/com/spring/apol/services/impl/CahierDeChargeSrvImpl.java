package com.spring.apol.services.impl;

import com.spring.apol.api.dto.CahierDeChargeDto;
import com.spring.apol.data.models.CahierDeCharge;
import com.spring.apol.data.models.Utilisateur;
import com.spring.apol.data.repositories.CahierDeChargeRepository;
import com.spring.apol.data.repositories.UtilisateurRepository; // Ajout du repository Utilisateur
import com.spring.apol.services.CahierDeChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CahierDeChargeSrvImpl implements CahierDeChargeService {

    private final CahierDeChargeRepository cahierDeChargeRepository;
    private final UtilisateurRepository utilisateurRepository; // Déclaration du repository Utilisateur

    @Autowired
    public CahierDeChargeSrvImpl(CahierDeChargeRepository cahierDeChargeRepository,
                                     UtilisateurRepository utilisateurRepository) {
        this.cahierDeChargeRepository = cahierDeChargeRepository;
        this.utilisateurRepository = utilisateurRepository; // Initialisation
    }

    @Override
    public CahierDeChargeDto saveCahierDeCharge(CahierDeChargeDto cahierDeChargeDto) {
        CahierDeCharge cahierDeCharge = cahierDeChargeDto.toEntity();
        CahierDeCharge savedCahierDeCharge = cahierDeChargeRepository.save(cahierDeCharge);
        return CahierDeChargeDto.fromEntity(savedCahierDeCharge);
    }

    @Override
    public CahierDeChargeDto updateCahierDeCharge(Long id, CahierDeChargeDto cahierDeChargeDto) {
        CahierDeCharge cahierDeCharge = cahierDeChargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cahier de charge non trouvé avec l'id: " + id));

        cahierDeCharge.setTexte(cahierDeChargeDto.getTexte());
        cahierDeCharge.setDate(cahierDeChargeDto.getDate());

        // Utilisation de findById pour récupérer les utilisateurs
        if (cahierDeChargeDto.getProfesseurId() != null) {
            Utilisateur professeur = utilisateurRepository.findById(cahierDeChargeDto.getProfesseurId())
                    .orElseThrow(() -> new RuntimeException("Professeur non trouvé avec l'id: " + cahierDeChargeDto.getProfesseurId()));
            cahierDeCharge.setProfesseur(professeur);
        }

        if (cahierDeChargeDto.getChefClasseId() != null) {
            Utilisateur chefClasse = utilisateurRepository.findById(cahierDeChargeDto.getChefClasseId())
                    .orElseThrow(() -> new RuntimeException("Chef de classe non trouvé avec l'id: " + cahierDeChargeDto.getChefClasseId()));
            cahierDeCharge.setChefClasse(chefClasse);
        }

        CahierDeCharge updatedCahierDeCharge = cahierDeChargeRepository.save(cahierDeCharge);
        return CahierDeChargeDto.fromEntity(updatedCahierDeCharge);
    }

    @Override
    public void deleteCahierDeCharge(Long id) {
        cahierDeChargeRepository.deleteById(id);
    }

    @Override
    public CahierDeChargeDto getCahierDeChargeById(Long id) {
        CahierDeCharge cahierDeCharge = cahierDeChargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cahier de charge non trouvé avec l'id: " + id));
        return CahierDeChargeDto.fromEntity(cahierDeCharge);
    }

    @Override
    public List<CahierDeChargeDto> getAllCahiersDeCharge() {
        return cahierDeChargeRepository.findAll().stream()
                .map(CahierDeChargeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CahierDeChargeDto> getCahiersDeChargeByProfesseur(Long professeurId) {
        return cahierDeChargeRepository.findByProfesseurId(professeurId).stream()
                .map(CahierDeChargeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CahierDeChargeDto> getCahiersDeChargeByChefClasse(Long chefClasseId) {
        return cahierDeChargeRepository.findByChefClasseId(chefClasseId).stream()
                .map(CahierDeChargeDto::fromEntity)
                .collect(Collectors.toList());
    }
}
