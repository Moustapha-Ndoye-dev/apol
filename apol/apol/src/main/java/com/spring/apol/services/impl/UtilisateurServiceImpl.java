package com.spring.apol.services.impl;

import com.spring.apol.api.dto.UtilisateurDto;
import com.spring.apol.data.enums.RoleUtilisateur;
import com.spring.apol.data.models.Classe;
import com.spring.apol.data.models.Utilisateur;
import com.spring.apol.data.repositories.ClasseRepository;
import com.spring.apol.data.repositories.UtilisateurRepository;
import com.spring.apol.services.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final ClasseRepository classeRepository;
    private final Random random = new Random();

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  ClasseRepository classeRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.classeRepository = classeRepository;
    }

    @Override
    public UtilisateurDto addUtilisateur(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = utilisateurDto.toEntity();

        // Generate login (email)
        String login = generateLogin(utilisateur.getNomComplet());
        utilisateur.setEmail(login);

        // Generate matricule
        String matricule = generateUniqueMatricule(utilisateur.getRole());
        utilisateur.setMatricule(matricule);

        // Generate password
        String password = generatePassword(utilisateur.getMoisNaissance(), utilisateur.getAnneeNaissance(), matricule);
        utilisateur.setPassword(password);

        if (utilisateur.getRole() == RoleUtilisateur.ETUDIANT || utilisateur.getRole() == RoleUtilisateur.CHEF_CLASSE) {
            if (utilisateurDto.getClasseId() != null) {
                Classe classe = classeRepository.findById(utilisateurDto.getClasseId())
                        .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
                utilisateur.setClasse(classe);
            }
        }

        utilisateur = utilisateurRepository.save(utilisateur);
        return UtilisateurDto.fromEntity(utilisateur);
    }

    private String generateLogin(String nomComplet) {
        String[] parts = nomComplet.split(" ", 2);
        String prenom = parts[0];
        String nom = parts.length > 1 ? parts[1] : "";

        return (prenom.toLowerCase().charAt(0) + nom.toLowerCase() + "@apol.edu").replaceAll("\\s+", "");
    }

    private String generateUniqueMatricule(RoleUtilisateur role) {
        String matricule;
        do {
            matricule = generateMatricule(role);
        } while (utilisateurRepository.findByMatricule(matricule).isPresent());
        return matricule;
    }

    private String generateMatricule(RoleUtilisateur role) {
        int firstDigit = switch (role) {
            case ADMINISTRATION -> 1;
            case PROFESSEUR -> 4;
            case ETUDIANT, CHEF_CLASSE -> 8;
        };
        int randomPart = 10000 + random.nextInt(90000);
        return String.format("%d%05d", firstDigit, randomPart);
    }

    private String generatePassword(String moisNaissance, int anneeNaissance, String matricule) {
        String moisPart = moisNaissance.toLowerCase().substring(0, Math.min(moisNaissance.length(), 3));
        String anneePart = String.format("%02d", anneeNaissance % 100);
        return moisPart + anneePart + matricule;
    }

    // Other methods remain unchanged

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

            if (utilisateurDto.getRole() == RoleUtilisateur.ETUDIANT || utilisateurDto.getRole() == RoleUtilisateur.CHEF_CLASSE) {
                if (utilisateurDto.getClasseId() != null) {
                    Classe classe = classeRepository.findById(utilisateurDto.getClasseId())
                            .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
                    utilisateur.setClasse(classe);
                }
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