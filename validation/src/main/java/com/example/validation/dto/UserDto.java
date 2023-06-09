package com.example.validation.dto;

import com.example.validation.constraints.annotations.BlackList;
import com.example.validation.constraints.annotations.EmailWhiteList;
import com.example.validation.constraints.annotations.Phone010;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;

    @NotBlank // 비어 있지 않음
    @BlackList(blacklist = {"malware.good", "trojan.jjang"})
    private String username;
    @Email // 형식이 이메일이어야 한다
    @EmailWhiteList
    private String email;
    @NotNull
    @Phone010
    private String phone;
    @NotNull
    @Min(value = 14, message = "14세 미만은 부모님의 동의가 필요합니다.")
    private Integer age;
    @Future
    private LocalDate validUntil;

    @NotNull // null이 아닌지만 검증
    private String notNullString;
    @NotEmpty // 길이가 0이 아닌지만 검증, String 이외에도 사용 가능
    private String notEmptyString;
    @NotBlank // 공백 문자로만 이루어졌는지 검증, 문자열에서만 사용 가능
    private String notBlankString;
}
/*
{
    "username": "huisu",
    "email": "huisu@gmail.com",
    "phone": "01012345678"
}
 */
