package com.spring.apol.data.repositories;

import com.spring.apol.data.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    // Rechercher des modules par nom
    List<Module> findByNomContainingIgnoreCase(String nom);

    // Trouver un module par son ID et vérifier son existence
    boolean existsById(Long id);

    // Ajoutez d'autres méthodes personnalisées si nécessaire
}
