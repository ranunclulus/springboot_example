package com.example.ralations.repo;

import com.example.ralations.Entities.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<InstructorEntity, Long> {
}
