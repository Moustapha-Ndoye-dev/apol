package com.spring.apol.services.impl;

import com.spring.apol.api.dto.ModuleDto;

import com.spring.apol.data.models.Module;
import com.spring.apol.data.repositories.ModuleRepository;
import com.spring.apol.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleServImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleServImpl(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @Override
    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Optional<Module> findById(Long id) {
        return moduleRepository.findById(id);
    }

    @Override
    public Module save(ModuleDto moduleDto) {
        // Convertir le DTO en entité
        Module module = moduleDto.toEntity();
        return moduleRepository.save(module);
    }

    @Override
    public void deleteById(Long id) {
        moduleRepository.deleteById(id);
    }

    @Override
    public List<Module> findByNom(String nom) {
        return moduleRepository.findByNomContainingIgnoreCase(nom);
    }
}
