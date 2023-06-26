package com.example.validation.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserDto {
    private Long id;

    @NotBlank // 비어 있지 않음
    private String username;
    @Email // 형식이 이메일이어야 한다
    private String email;
    private String phone;
}
/*
{
    "username": "huisu",
    "email": "huisu@gmail.com",
    "phone": "01012345678"
}
 */
