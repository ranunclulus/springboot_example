package com.example.jpa.repos;

import com.example.jpa.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
    //JpaRepository<내가 다룰 Entity, Entity의 PK>
        extends JpaRepository<StudentEntity, Long> {

}
