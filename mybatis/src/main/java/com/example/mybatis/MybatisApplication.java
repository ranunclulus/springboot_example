package com.example.mybatis;

import com.example.mybatis.dao.StudentDao;
import com.example.mybatis.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext =
				SpringApplication.run(MybatisApplication.class, args);

		StudentDao dao = applicationContext.getBean(StudentDao.class);
		System.out.println(dao.readAllXml());

	}

}
