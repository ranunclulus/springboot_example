package com.example.crud;

import com.example.crud.model.StudentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    // 복수의 StudentDto를 담는 변수
    private List<StudentDto> studentDtoList = new ArrayList<>();
    // 새로운 StudentDto를 생성하는 메소
    public void newStudentDto(long id, String name, String email) {
        StudentDto studentDto = new StudentDto(id, name, email);
        studentDtoList.add(studentDto);
    }
}
