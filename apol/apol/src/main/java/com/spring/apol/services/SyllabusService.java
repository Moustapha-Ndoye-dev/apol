package com.spring.apol.services;

import com.spring.apol.api.dto.SyllabusDto;
import java.util.List;

public interface SyllabusService {

    SyllabusDto createSyllabus(SyllabusDto syllabusDto);

    SyllabusDto updateSyllabus(Long id, SyllabusDto syllabusDto);

    void deleteSyllabus(Long id);

    SyllabusDto getSyllabusById(Long id);

    List<SyllabusDto> getAllSyllabi();

    List<SyllabusDto> getSyllabiByModuleId(Long moduleId);

    List<SyllabusDto> getSyllabiByProfesseurId(Long professeurId);

    List<SyllabusDto> searchSyllabiByTitle(String titre);
}
