package com.example.ralations;

import com.example.ralations.Entities.InstructorEntity;
import com.example.ralations.Entities.LectureEntity;
import com.example.ralations.repo.InstructorRepository;
import com.example.ralations.repo.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("instructor")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorRepository instructorRepository;
    private final LectureRepository lectureRepository;

    @GetMapping("{id}/lectures")
    public void readInstructorLectures(@PathVariable("id") Long id) {
        Optional<InstructorEntity> optionalInstructorEntity
                = instructorRepository.findById(id);
        if(optionalInstructorEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        for(LectureEntity lecture:optionalInstructorEntity.get().getLectures()) {
            log.info(lecture.getName());
        }
    }
}
