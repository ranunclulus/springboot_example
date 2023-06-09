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

    public StudentService() {
        createStudent("alex", "alex@gmail.com");
        createStudent("brad", "brad@gmail.com");
        createStudent("chad", "chad@gmail.com");
    }
    // 새로운 StudentDto를 생성하는 메소
    public StudentDto createStudent(String name, String email) {
        StudentDto studentDto = new StudentDto(nextId++, name, email);
        studentDtoList.add(studentDto);
        return studentDto;
    }

    public List<StudentDto> readStudentAll() {
        return this.studentDtoList;
    }

    public StudentDto readStudent(Long id) {
        for (StudentDto student: studentDtoList) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public StudentDto updateStudent(Long id, String name, String email) {
        /*
        for (StudentDto student: studentDtoList) {
            if(student.getId().equals(id)){
                student.setName(name);
                student.setEmail(email);
                return student;
            }
        }
        return null;
        */
        int target = -1;
        for (int i = 0; i < studentDtoList.size(); i++) {
            if(studentDtoList.get(i).getId().equals(id)) {
                target = i;
                break;
            }
        }

        if(target != -1) {
            studentDtoList.get(target).setName(name);
            studentDtoList.get(target).setEmail(email);
            return studentDtoList.get(target);
        }
        else return null;
    }
}
