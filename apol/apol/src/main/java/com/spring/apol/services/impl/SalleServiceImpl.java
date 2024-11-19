package com.spring.apol.services.impl;

import com.spring.apol.api.dto.SalleDto;
import com.spring.apol.data.models.Salle;
import com.spring.apol.data.repositories.SalleRepository;
import com.spring.apol.services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalleServiceImpl implements SalleService {

    private final SalleRepository salleRepository;

    // Injection du repository
    @Autowired
    public SalleServiceImpl(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    @Override
    public SalleDto createSalle(SalleDto salleDto) {
        // Vérifier si une salle avec le même nom existe déjà
        Optional<Salle> existingSalle = salleRepository.findByNom(salleDto.getNom());
        if (existingSalle.isPresent()) {
            throw new RuntimeException("Une salle avec ce nom existe déjà.");
        }

        // Convertir le DTO en entité
        Salle salle = salleDto.toEntity();

        // Sauvegarder l'entité
        Salle savedSalle = salleRepository.save(salle);

        // Convertir l'entité sauvegardée en DTO
        return SalleDto.fromEntity(savedSalle);
    }


    @Override
    public SalleDto updateSalle(Long id, SalleDto salleDto) {
        Optional<Salle> existingSalle = salleRepository.findById(id);
        if (existingSalle.isPresent()) {
            Salle salle = existingSalle.get();
            salle.setNom(salleDto.getNom());  // Mettre à jour les informations de la salle
            Salle updatedSalle = salleRepository.save(salle);  // Sauvegarder la mise à jour
            return SalleDto.fromEntity(updatedSalle);  // Retourner le DTO mis à jour
        }
        return null;  // Si la salle n'existe pas, retourner null (ou vous pouvez gérer autrement)
    }

    @Override
    public SalleDto getSalleById(Long id) {
        Optional<Salle> salle = salleRepository.findById(id);
        return salle.map(SalleDto::fromEntity).orElse(null); // Retourner le DTO ou null si non trouvé
    }

    @Override
    public List<SalleDto> getAllSalles() {
        List<Salle> salles = salleRepository.findAll();
        return salles.stream()
                .map(SalleDto::fromEntity)  // Convertir chaque entité en DTO
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSalle(Long id) {
        salleRepository.deleteById(id); // Supprimer la salle par ID
    }
}
