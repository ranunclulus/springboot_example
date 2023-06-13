package com.example.mybatis.mapper;

import com.example.mybatis.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper //Mapper가 붙은 클래스를 MyBatis가 데이터베이스 통신에 사용할 준비
public interface StudentMapper {
    // SELECT * FROM students; 를 실행할 메소드를 만드는데
    // 복수개의 students를 반환하게 하는 return type -> List<Student>
    @Select("SELECT * FROM students")
    List<Student> selectStudentAll();
}
