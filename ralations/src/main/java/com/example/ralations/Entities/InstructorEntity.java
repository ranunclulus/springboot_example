package com.example.ralations.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lecture")
public class InstructorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

}
