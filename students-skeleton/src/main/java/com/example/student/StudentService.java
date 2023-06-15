package com.example.student;

import com.example.student.dto.StudentDto;
import com.example.student.entity.StudentEntity;
import com.example.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public StudentDto createStudent(StudentDto dto) {
        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setName(dto.getName());
        studentEntity.setAge(dto.getAge());
        studentEntity.setPhone(dto.getPhone());
        studentEntity.setEmail(dto.getEmail());
        this.repository.save(studentEntity);
        return StudentDto
                .fromEntity(studentEntity);
    }

    // READ
    public void readStudent() {}

    // READ ALL
    public List<StudentDto> readStudentAll() {
        List<StudentDto> studentDtoList = new ArrayList<>();

        for (StudentEntity studentEntity: this.repository.findAll()
             ) {
            studentDtoList.add(StudentDto.fromEntity(studentEntity));
        }
        return studentDtoList;
    }

    // UPDATE
    public void updateStudent() {}

    // DELETE
    public void deleteStudent() {}
}
