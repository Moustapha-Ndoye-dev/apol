package com.spring.apol.services;

import com.spring.apol.api.dto.FiliereDto;

import java.util.List;
import java.util.Optional;

public interface FiliereService {
    FiliereDto createFiliere(FiliereDto filiereDto);
    List<FiliereDto> getAllFilieres();
    Optional<FiliereDto> getFiliereById(Long id);
    void deleteFiliere(Long id);
}
