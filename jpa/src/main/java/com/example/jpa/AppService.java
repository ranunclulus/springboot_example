package com.example.jpa;

import com.example.jpa.entities.StudentEntity;
import com.example.jpa.repos.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void readStudent(Long id) {
        // 반환된 값이 있을 수도 있고 없을 수도 있다
        Optional<StudentEntity> optionalStudentEntity
                = this.studentRepository.findById(id);
        // 1. 결과로 실제 데이터가 온 경우
        if (optionalStudentEntity.isPresent()) {
            // 실제 결과가 있을 때만 출력한다 이런 개념
            System.out.println(optionalStudentEntity.get());
        }
        // 2. 결과가 null일 경우
        else {
            System.out.println("no result");
        }
    }

    // READ ALL
    public void readStudentAll() {
        System.out.println(this.studentRepository.findAll());
    }

    // UPDATE
    // 어떤 학생을 수정할 것인지 지정 필요
    public void updateStudent(
            Long id,
            String name // 이름만 바꿔 보기로 가정
            ) {
        // 수정할 Entity를 회수
        Optional<StudentEntity> optionalEntity
                = this.studentRepository.findById(id);
        if(optionalEntity.isPresent()){
            // 수정할 애를 찾은 경우
            StudentEntity target = optionalEntity.get(); // 실제 객체 회수
            target.setName(name); // 이름 수정
            this.studentRepository.save(target); // 레포에 저장
        }
        else {
            System.out.println("could not find");
        }

    }
    // DELETE
    public void deleteStudent(Long id) {
        Optional<StudentEntity> optionalEntity = this.studentRepository.findById(id);
        if(optionalEntity.isPresent()) {
            StudentEntity studentEntity =
                    optionalEntity.get();
            this.studentRepository.delete(studentEntity);
        }
        else {
            System.out.println("could not found");
        }
    }

    /*
    public List<Object> readStudentAll() {
        List<Object> queryResult = repository.selectStudentAll();
        return queryResult;
    }

     */
}