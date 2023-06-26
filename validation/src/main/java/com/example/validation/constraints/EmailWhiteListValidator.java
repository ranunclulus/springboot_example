package com.example.validation.constraints;

import com.example.validation.constraints.annotations.EmailWhiteList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class EmailWhiteListValidator
    // 수동 사용자 지정 유효성 검사를 위해 구현해야 하는 인터페이스
    // <쓸 어노테이션, 검증을 적용할 데이터의 타입>
    implements ConstraintValidator<EmailWhiteList, String> {

    private final Set<String> whiteList;

    public EmailWhiteListValidator() {
        this.whiteList = new HashSet<>();
        this.whiteList.add("gmail.com");
        this.whiteList.add("naver.com");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 유효한 값일 때 true를 반환
        String[] split = value.split("@");
        String domain = split[split.length - 1];
        return whiteList.contains(domain);
    }
}
