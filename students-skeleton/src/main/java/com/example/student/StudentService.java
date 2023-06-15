package com.example.student;

import com.example.student.dto.StudentDto;
import com.example.student.entity.StudentEntity;
import com.example.student.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public StudentDto readStudent(Long id) {
        Optional<StudentEntity> optionalEntity =
                this.repository.findById(id);
        if (optionalEntity.isPresent()) {
            return StudentDto.fromEntity(optionalEntity.get());
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

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
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Optional<StudentEntity> optionalEntity
                = repository.findById(id);

        if(optionalEntity.isPresent()) {
            StudentEntity targetEntity = optionalEntity.get();
            targetEntity.setName(studentDto.getName());
            targetEntity.setAge(studentDto.getAge());
            targetEntity.setPhone(studentDto.getPhone());
            targetEntity.setEmail(studentDto.getEmail());
            return StudentDto.fromEntity(repository.save(targetEntity));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    public void deleteStudent(Long id) {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
