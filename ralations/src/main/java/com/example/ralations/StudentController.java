package com.example.ralations;

import com.example.ralations.Entities.LectureEntity;
import com.example.ralations.Entities.StudentEntity;
import com.example.ralations.repo.LectureRepository;
import com.example.ralations.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;

    @PutMapping("{id}/lectures/{lectureId}")
    public void updateStudentLectures(
            @PathVariable("id") Long id,
            @PathVariable("lectureId") Long lectureId
    ) {
        Optional<StudentEntity> optionalStudentEntity
                = studentRepository.findById(id);
        if(optionalStudentEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<LectureEntity> optionalLectureEntity =
                lectureRepository.findById(lectureId);
        if(optionalLectureEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        StudentEntity student = optionalStudentEntity.get();
        LectureEntity lecture = optionalLectureEntity.get();

        student.getAttending().add(lecture);
        studentRepository.save(student);
        lecture.getStudents().add(student);
        lectureRepository.save(lecture);
    }
}
