package com.example.student.dto;

import com.example.student.entity.StudentEntity;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String email;

    // Static Factory Method Pattern
    public static StudentDto fromEntity(StudentEntity studentEntity) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(studentEntity.getId());
        studentDto.setName(studentEntity.getName());
        studentDto.setAge(studentEntity.getAge());
        studentDto.setPhone(studentEntity.getPhone());
        studentDto.setEmail(studentEntity.getEmail());
        return studentDto;
    }
}
