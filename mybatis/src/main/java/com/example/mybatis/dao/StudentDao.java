package com.example.mybatis.dao;

import com.example.mybatis.mapper.StudentMapper;
import com.example.mybatis.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

@Mapper
public class StudentDao {
    private final SqlSessionFactory sessionFactory;
    public StudentDao(SqlSessionFactory sessionFactory) {
        this.sessionFactory =sessionFactory;
    }

    public List<Student> readStudentAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            return studentMapper.selectStudentAll();
        }
    }
}
