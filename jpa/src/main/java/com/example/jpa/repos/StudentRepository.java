package com.example.jpa.repos;

import com.example.jpa.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository
    //JpaRepository<내가 다룰 Entity, Entity의 PK>
        extends JpaRepository<StudentEntity, Long> {

    // Spring Data JPA - Query Method
    // 메소드 이름을 우리가 조회하고 싶은 조건을 붙여서 만든다.
    /*
    이때의 규칙
    findBy || findAllBy 둘 중 하나로 시작
    (findBy || findALlBy) * [(Column + 조건) * n] + [OrderBy + Column]
     */

    // SELECT * FROM students ORDER BY age;
    List<StudentEntity> findAllByOrderByAge();

    // SELECT * FROM students ORDER BY age DESC;
    List<StudentEntity> findAllByOrderByAgeDesc();

    // SELECT * FROM students WHERE age < 21;
    List<StudentEntity> findAllByAgeLessThan(Integer age);

    // SELECT * FROM students phone LIKE '010-%'
    List<StudentEntity> findAllByPhoneStartingWith(String phone);

}
