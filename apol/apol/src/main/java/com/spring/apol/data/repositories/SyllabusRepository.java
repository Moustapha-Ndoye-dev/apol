package com.spring.apol.data.repositories;

import com.spring.apol.data.models.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {

    List<Syllabus> findByModuleId(Long moduleId);

    List<Syllabus> findByProfesseurId(Long professeurId);

    List<Syllabus> findByTitreContaining(String titre);
}
