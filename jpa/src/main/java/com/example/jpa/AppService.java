package com.example.jpa;

import com.example.jpa.entities.StudentEntity;
import com.example.jpa.repos.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {
    private final AppRepository repository;
    private final StudentRepository studentRepository;

    public AppService(AppRepository repository, StudentRepository studentRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
    }

    // CREATE
    public void createStudent(
            String name,
            Integer age,
            String phone,
            String email
    ) {
        // 새로운 학생을 만들고 싶다
        StudentEntity newEntity = new StudentEntity();
        newEntity.setName(name);
        newEntity.setAge(age);
        newEntity.setPhone(phone);
        newEntity.setEmail(email);
        this.studentRepository.save(newEntity);
    }

    // READ
    // READ ALL
    // UPDATE
    // DELETE

    public List<Object> readStudentAll() {
        List<Object> queryResult = repository.selectStudentAll();
        return queryResult;
    }
}
