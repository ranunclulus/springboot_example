package com.example.jpa;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppRepository {
    public List<Object> selectStudentAll() {
        return new ArrayList<>();
    }
}
