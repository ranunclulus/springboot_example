package com.example.crud;

import com.example.crud.model.StudentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    // 복수의 StudentDto를 담는 변수
    private final List<StudentDto> studentDtoList = new ArrayList<>();
    private long nextId = 1L;
    // 새로운 StudentDto를 생성하는 메소
    public StudentDto createStudent(String name, String email) {
        StudentDto studentDto = new StudentDto(nextId++, name, email);
        studentDtoList.add(studentDto);
        return studentDto;
    }
}
