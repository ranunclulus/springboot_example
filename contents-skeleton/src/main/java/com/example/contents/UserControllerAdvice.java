package com.example.contents;

import com.example.contents.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
// 각 Controller에 나누어진 ExceptionHandler 메소드를 모으는 역ㄸ
public class UserControllerAdvice {
    @ExceptionHandler(IllegalStateException.class)
    public ResponseDto handleIllegalState(IllegalStateException exception) {
        ResponseDto response = new ResponseDto();
        response.setMessage("UserControllerAdvice에서 처리한 예외입니다.");
        return response;
    }
}
