package com.example.ralations.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data

@Entity
@Table(name = "lecture")
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String course_name;
    private Integer startTime;
    private Integer endTime;

    @ManyToOne
    private InstructorEntity instructor;
}
