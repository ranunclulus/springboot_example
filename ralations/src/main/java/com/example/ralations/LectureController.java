package com.example.ralations;

import com.example.ralations.Entities.InstructorEntity;
import com.example.ralations.Entities.LectureEntity;
import com.example.ralations.repo.InstructorRepository;
import com.example.ralations.repo.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("lectures")
@RequiredArgsConstructor
public class LectureController {
    private final LectureRepository lectureRepository;
    private final InstructorRepository instructorRepository;

    // 강의에 강사를 배정한다
    @PutMapping("{id}/instructor/{instructorId}")
    // 응답 바디가 없을 것이다
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLectureInstructor(
            @PathVariable("id") Long id,
            @PathVariable("instructorId") Long instructorId
    ) {

        Optional<LectureEntity> optionalLectureEntity
        = lectureRepository.findById(id);
        if(optionalLectureEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<InstructorEntity> optionalInstructorEntity
                = instructorRepository.findById(instructorId);
        if(optionalInstructorEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        LectureEntity lecture = optionalLectureEntity.get();
        InstructorEntity instructor = optionalInstructorEntity.get();
        lecture.setInstructor(instructor);
        lectureRepository.save(lecture);
    }

    // id 강의의 강사를 반환하는 엔드포인트
    @GetMapping("{id}/instructor")
    public void readLectureInstructor(
            @PathVariable("id") Long id
    ) {
        Optional<LectureEntity> optionalLectureEntity
                = lectureRepository.findById(id);
        if(optionalLectureEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        log.info(optionalLectureEntity.get().getInstructor().toString());
    }
}
