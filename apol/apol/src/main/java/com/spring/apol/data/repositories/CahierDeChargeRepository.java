package com.spring.apol.data.repositories;

import com.spring.apol.data.models.CahierDeCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CahierDeChargeRepository extends JpaRepository<CahierDeCharge, Long> {

    // Méthode pour récupérer les cahiers de charge par professeur
    List<CahierDeCharge> findByProfesseurId(Long professeurId);

    // Méthode pour récupérer les cahiers de charge par chef de classe
    List<CahierDeCharge> findByChefClasseId(Long chefClasseId);
}
