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

    public void updateStudent(Long id, String name, String email) {
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

        }
    }


    public boolean deleteStudent(Long id) {
        int target = -1;
        // 학생 리스트를 살펴보며
        for (int i = 0; i < studentDtoList.size(); i++) {
            // 대상을 선정한다.
            if (studentDtoList.get(i).getId().equals(id)) {
                target = i;
                break;
            }
        }

        // 검색 성공시
        if (target != -1){
            // 삭제
            studentDtoList.remove(target);
            return true;
        }
        else return false;
    }

}
