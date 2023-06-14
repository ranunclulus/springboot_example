package com.example.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/*
* CREATE TABLE students (
* id INTEGER PRIMARY KEY AUTOINCREMEMT,
* name TEXT,
* age INTEGER,
* phone TEXT,
* email TEXT)
* */
@Data
@Entity

public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String email;
}
