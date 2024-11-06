package com.spring.apol;

import com.spring.apol.api.dto.UtilisateurDto;
import com.spring.apol.data.enums.RoleUtilisateur;
import com.spring.apol.data.models.Utilisateur;
import com.spring.apol.data.repositories.UtilisateurRepository;
import com.spring.apol.services.impl.UtilisateurServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(false)
class UtilisateurServiceImplTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurServiceImpl utilisateurService;

    @BeforeEach
    void setUp() {
        // Initialisation automatique via @Autowired
    }

    @Test
    void addUtilisateur_ShouldReturnUtilisateurDto_WhenUtilisateurIsSaved() {
        // Arrange
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setEmail("test1@example.com");
        utilisateurDto.setNomComplet("Jean Dupont");
        utilisateurDto.setPassword("password123");
        utilisateurDto.setMoisNaissance("Janvier");
        utilisateurDto.setAnneeNaissance(1995);
        utilisateurDto.setTelephone("0761234567");
        utilisateurDto.setMatricule("12345");
        utilisateurDto.setRole(RoleUtilisateur.ETUDIANT);

        // Act
        UtilisateurDto result = utilisateurService.addUtilisateur(utilisateurDto);

        // Assert
        assertNotNull(result);
        assertEquals("test1@example.com", result.getEmail());

        // Affichage dans la console
        System.out.println("Utilisateur ajouté : " + result);
    }

    @Test
    void updateUtilisateur_ShouldReturnUpdatedUtilisateurDto_WhenUtilisateurExists() {
        // Arrange
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setNomComplet("Jean Dupont");

        // Créez un utilisateur dans la base de données pour effectuer la mise à jour
        UtilisateurDto savedUtilisateur = utilisateurService.addUtilisateur(utilisateurDto);

        // Mise à jour
        UtilisateurDto updateDto = new UtilisateurDto();
        updateDto.setNomComplet("Jean Dupont Modifié");

        // Act
        Optional<UtilisateurDto> result = utilisateurService.updateUtilisateur(savedUtilisateur.getId(), updateDto);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Jean Dupont Modifié", result.get().getNomComplet());

        // Affichage dans la console
        System.out.println("Utilisateur après mise à jour : " + result.get());
    }

    @Test
    void getAllUtilisateurs_ShouldReturnListOfUtilisateurDto() {
        // Act
        List<UtilisateurDto> result = utilisateurService.getAllUtilisateurs();

        // Assert
        assertNotNull(result);
        assertTrue(result.size() > 0);

        // Affichage dans la console
        System.out.println("Liste des utilisateurs : ");
        result.forEach(System.out::println);
    }

    @Test
    void getUtilisateurById_ShouldReturnUtilisateurDto_WhenUtilisateurExists() {
        // Arrange
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setNomComplet("Jean Dupont");
        utilisateurDto.setEmail("jean@example.com");

        // Créez un utilisateur dans la base de données pour effectuer la recherche
        UtilisateurDto savedUtilisateur = utilisateurService.addUtilisateur(utilisateurDto);

        // Act
        Optional<UtilisateurDto> result = utilisateurService.getUtilisateurById(savedUtilisateur.getId());

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Jean Dupont", result.get().getNomComplet());

        // Affichage dans la console
        System.out.println("Utilisateur trouvé par ID : " + result.get());
    }

    @Test
    void deleteUtilisateur_ShouldCallDeleteById_WhenUtilisateurExists() {
        // Arrange
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setNomComplet("Utilisateur à supprimer");

        // Créez un utilisateur dans la base de données pour le supprimer
        UtilisateurDto savedUtilisateur = utilisateurService.addUtilisateur(utilisateurDto);

        // Act
        utilisateurService.deleteUtilisateur(savedUtilisateur.getId());

        // Assert
        Optional<UtilisateurDto> deletedUtilisateur = utilisateurService.getUtilisateurById(savedUtilisateur.getId());
        assertTrue(deletedUtilisateur.isEmpty());

        // Affichage dans la console
        System.out.println("Utilisateur supprimé avec ID : " + savedUtilisateur.getId());
    }

    @Test
    void getUtilisateurByEmail_ShouldReturnUtilisateurDto_WhenUtilisateurExists() {
        // Arrange
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setEmail("jean@example.com");
        utilisateurDto.setNomComplet("Jean Dupont");

        // Créez un utilisateur dans la base de données pour effectuer la recherche par email
        utilisateurService.addUtilisateur(utilisateurDto);

        // Act
        Optional<UtilisateurDto> result = utilisateurService.getUtilisateurByEmail("jean@example.com");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Jean Dupont", result.get().getNomComplet());

        // Affichage dans la console
        System.out.println("Utilisateur trouvé par email : " + result.get());
    }

    @Test
    void getUtilisateursByRole_ShouldReturnListOfUtilisateurDto_WhenUtilisateursExist() {
        // Arrange
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setNomComplet("Etudiant");
        utilisateurDto.setRole(RoleUtilisateur.ETUDIANT);

        // Créez un utilisateur dans la base de données pour filtrer par rôle
        utilisateurService.addUtilisateur(utilisateurDto);

        // Act
        List<UtilisateurDto> result = utilisateurService.getUtilisateursByRole(RoleUtilisateur.ETUDIANT);

        // Assert
        assertFalse(result.isEmpty());

        // Affichage dans la console
        System.out.println("Utilisateurs trouvés par rôle : ");
        result.forEach(System.out::println);
    }
}
