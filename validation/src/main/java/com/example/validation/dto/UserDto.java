package com.example.validation.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserDto {
    private Long id;

    @NotBlank // 비어 있지 않음
    private String username;
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
