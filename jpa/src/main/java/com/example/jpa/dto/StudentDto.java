package com.example.jpa.dto;

import com.example.jpa.entities.StudentEntity;
import lombok.Data;

@Data
public class StudentDto {
    private Long id; // Entity.id
    private String name; // Entity.name
    private String email; // Entity.email

    // 정적 메소드: 클래스 자체에 속한 메소드
    // 만들어진 StudentDto에서 호출하는 것이 아니라 클래스 틀에서 호출
    public static StudentDto
        fromEntity(StudentEntity studentEntity) {
        StudentDto dto = new StudentDto();
        dto.setId(studentEntity.getId());
        dto.setName(studentEntity.getName());
        dto.setEmail(studentEntity.getEmail());
        return dto;
    }
}
