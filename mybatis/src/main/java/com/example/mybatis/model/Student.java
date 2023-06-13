package com.example.mybatis.model;

import lombok.Data;

@Data //Getter, Setter, RequiredArgConstructor, ToString, EqualsandHashCode
public class Student {
    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String email;
}
