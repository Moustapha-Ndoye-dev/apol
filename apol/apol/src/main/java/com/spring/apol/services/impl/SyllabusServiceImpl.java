package com.spring.apol.services.impl;

import com.spring.apol.api.dto.SyllabusDto;
import com.spring.apol.data.models.Syllabus;
import com.spring.apol.data.models.Module; // Ajout de l'importation pour le module
import com.spring.apol.data.repositories.SyllabusRepository;
import com.spring.apol.data.repositories.ModuleRepository; // Ajout de l'importation pour le repository du module
import com.spring.apol.services.SyllabusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SyllabusServiceImpl implements SyllabusService {

    private final SyllabusRepository syllabusRepository;

    private final ModuleRepository moduleRepository; // Ajout du repository du module

    public SyllabusServiceImpl(SyllabusRepository syllabusRepository, ModuleRepository moduleRepository) {
        this.syllabusRepository = syllabusRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    public SyllabusDto createSyllabus(SyllabusDto syllabusDto) {
        Syllabus syllabus = syllabusDto.toEntity();

        // Récupérer le module associé si un ID est fourni
        if (syllabusDto.getModuleId() != null) {
            Module module = moduleRepository.findById(syllabusDto.getModuleId())
                    .orElseThrow(() -> new RuntimeException("Module not found"));
            syllabus.setModule(module);
        }

        syllabus = syllabusRepository.save(syllabus);
        return SyllabusDto.fromEntity(syllabus);
    }

    @Override
    public SyllabusDto updateSyllabus(Long id, SyllabusDto syllabusDto) {
        Syllabus existingSyllabus = syllabusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Syllabus not found"));

        // Mettre à jour les attributs sans modifier le professeur
        existingSyllabus.setTitre(syllabusDto.getTitre());
        existingSyllabus.setObjectifs(syllabusDto.getObjectifs());
        existingSyllabus.setDateCreation(syllabusDto.getDateCreation()); // Mettre à jour la date
        existingSyllabus.setDureeEnSemaines(syllabusDto.getDureeEnSemaines());
        existingSyllabus.setCheminDocument(syllabusDto.getCheminDocument());

        // Mettre à jour le module si un ID est fourni
        if (syllabusDto.getModuleId() != null) {
            Module module = moduleRepository.findById(syllabusDto.getModuleId())
                    .orElseThrow(() -> new RuntimeException("Module not found"));
            existingSyllabus.setModule(module);
        }

        // Pas de mise à jour du professeur ici
        existingSyllabus = syllabusRepository.save(existingSyllabus);
        return SyllabusDto.fromEntity(existingSyllabus);
    }

    @Override
    public void deleteSyllabus(Long id) {
        syllabusRepository.deleteById(id);
    }

    @Override
    public SyllabusDto getSyllabusById(Long id) {
        Syllabus syllabus = syllabusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Syllabus not found"));
        return SyllabusDto.fromEntity(syllabus);
    }

    @Override
    public List<SyllabusDto> getAllSyllabi() {
        return syllabusRepository.findAll()
                .stream()
                .map(SyllabusDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SyllabusDto> getSyllabiByModuleId(Long moduleId) {
        return syllabusRepository.findByModuleId(moduleId)
                .stream()
                .map(SyllabusDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SyllabusDto> getSyllabiByProfesseurId(Long professeurId) {
        return syllabusRepository.findByProfesseurId(professeurId)
                .stream()
                .map(SyllabusDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SyllabusDto> searchSyllabiByTitle(String titre) {
        return syllabusRepository.findByTitreContaining(titre)
                .stream()
                .map(SyllabusDto::fromEntity)
                .collect(Collectors.toList());
    }
}
