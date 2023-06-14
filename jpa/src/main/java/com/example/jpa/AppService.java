package com.example.jpa;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {
    private final AppRepository repository;

    public AppService(AppRepository repository) {
        this.repository = repository;
    }

    public List<Object> readStudentAll() {
        List<Object> queryResult = repository.selectStudentAll();
        return queryResult;
    }
}
