package com.spring.apol.services;

import com.spring.apol.api.dto.ModuleDto;
import com.spring.apol.data.models.Module;

import java.util.List;
import java.util.Optional;

public interface ModuleService {
    List<Module> findAll();

    Optional<Module> findById(Long id);

    Module save(ModuleDto moduleDto);

    void deleteById(Long id);

    List<Module> findByNom(String nom); // Méthode de recherche
}
