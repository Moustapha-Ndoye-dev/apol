package com.spring.apol.data.repositories;

import com.spring.apol.data.models.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    // Méthode pour trouver des classes par le nom
    List<Classe> findByNom(String nom);

    // Méthode pour trouver des classes par filière ID
}
