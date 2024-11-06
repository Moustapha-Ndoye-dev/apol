package com.spring.apol.services.impl;

import com.spring.apol.api.dto.UtilisateurDto;
import com.spring.apol.data.enums.RoleUtilisateur;
import com.spring.apol.data.models.Classe;
import com.spring.apol.data.models.Filiere;
import com.spring.apol.data.models.Utilisateur;
import com.spring.apol.data.repositories.ClasseRepository;
import com.spring.apol.data.repositories.FiliereRepository;
import com.spring.apol.data.repositories.UtilisateurRepository;
import com.spring.apol.services.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final ClasseRepository classeRepository;
    private final FiliereRepository filiereRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  ClasseRepository classeRepository,
                                  FiliereRepository filiereRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.classeRepository = classeRepository;
        this.filiereRepository = filiereRepository;
    }

    @Override
    public UtilisateurDto addUtilisateur(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = utilisateurDto.toEntity();

        // Set classe and filiere only if the role is ETUDIANT or CHEF_DE_CLASSE
        if (utilisateur.getRole() == RoleUtilisateur.ETUDIANT || utilisateur.getRole() == RoleUtilisateur.CHEF_CLASSE) {
            if (utilisateurDto.getClasseId() != null) {
                Classe classe = classeRepository.findById(utilisateurDto.getClasseId())
                        .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
                utilisateur.setClasse(classe);
            }
            if (utilisateurDto.getFiliereId() != null) {
                Filiere filiere = filiereRepository.findById(utilisateurDto.getFiliereId())
                        .orElseThrow(() -> new RuntimeException("Filiere non trouvée"));
                utilisateur.setFiliereId(filiere);
            }
        }

        utilisateur = utilisateurRepository.save(utilisateur);
        return UtilisateurDto.fromEntity(utilisateur);
    }

    @Override
    public Optional<UtilisateurDto> updateUtilisateur(Long id, UtilisateurDto utilisateurDto) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(id);

        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();

            if (utilisateurDto.getNomComplet() != null) {
                utilisateur.setNomComplet(utilisateurDto.getNomComplet());
            }
            if (utilisateurDto.getEmail() != null) {
                utilisateur.setEmail(utilisateurDto.getEmail());
            }
            if (utilisateurDto.getPassword() != null) {
                utilisateur.setPassword(utilisateurDto.getPassword());
            }
            if (utilisateurDto.getMoisNaissance() != null) {
                utilisateur.setMoisNaissance(utilisateurDto.getMoisNaissance());
            }
            utilisateur.setAnneeNaissance(utilisateurDto.getAnneeNaissance());
            if (utilisateurDto.getTelephone() != null) {
                utilisateur.setTelephone(utilisateurDto.getTelephone());
            }
            if (utilisateurDto.getMatricule() != null) {
                utilisateur.setMatricule(utilisateurDto.getMatricule());
            }
            if (utilisateurDto.getRole() != null) {
                utilisateur.setRole(utilisateurDto.getRole());
            }

            // Update classe and filiere if role is ETUDIANT or CHEF_DE_CLASSE
            if (utilisateurDto.getRole() == RoleUtilisateur.ETUDIANT || utilisateurDto.getRole() == RoleUtilisateur.CHEF_CLASSE) {
                if (utilisateurDto.getClasseId() != null) {
                    Classe classe = classeRepository.findById(utilisateurDto.getClasseId())
                            .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
                    utilisateur.setClasse(classe);
                }
                if (utilisateurDto.getFiliereId() != null) {
                    Filiere filiere = filiereRepository.findById(utilisateurDto.getFiliereId())
                            .orElseThrow(() -> new RuntimeException("Filiere non trouvée"));
                    utilisateur.setFiliereId(filiere);
                }
            } else {
                utilisateur.setClasse(null);
                utilisateur.setFiliereId(null);
            }

            utilisateurRepository.save(utilisateur);
            return Optional.of(UtilisateurDto.fromEntity(utilisateur));
        }

        return Optional.empty();
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateurs() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .toList();
    }

    @Override
    public Optional<UtilisateurDto> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id)
                .map(UtilisateurDto::fromEntity);
    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public Optional<UtilisateurDto> getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .map(UtilisateurDto::fromEntity);
    }

    @Override
    public Optional<UtilisateurDto> getUtilisateurByMatricule(String matricule) {
        return utilisateurRepository.findByMatricule(matricule)
                .map(UtilisateurDto::fromEntity);
    }

    @Override
    public List<UtilisateurDto> getUtilisateursByRole(RoleUtilisateur role) {
        return utilisateurRepository.findByRole(role).stream()
                .map(UtilisateurDto::fromEntity)
                .toList();
    }

    @Override
    public List<UtilisateurDto> searchUtilisateursByNomComplet(String nomComplet) {
        return utilisateurRepository.findByNomCompletContaining(nomComplet).stream()
                .map(UtilisateurDto::fromEntity)
                .toList();
    }
}
