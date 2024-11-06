package com.spring.apol.services.impl;

import com.spring.apol.api.dto.FiliereDto;
import com.spring.apol.data.models.Filiere;
import com.spring.apol.data.repositories.FiliereRepository;
import com.spring.apol.services.FiliereService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FiliereServImpl implements FiliereService {

    private final FiliereRepository filiereRepository;
    public FiliereServImpl(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    @Override
    public FiliereDto createFiliere(FiliereDto filiereDto) {
        Filiere filiere = filiereDto.toEntity();
        filiere = filiereRepository.save(filiere);
        return FiliereDto.fromEntity(filiere);
    }

    @Override
    public List<FiliereDto> getAllFilieres() {
        return filiereRepository.findAll().stream()
                .map(FiliereDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FiliereDto> getFiliereById(Long id) {
        return filiereRepository.findById(id).map(FiliereDto::fromEntity);
    }

    @Override
    public void deleteFiliere(Long id) {
        filiereRepository.deleteById(id);
    }
}
