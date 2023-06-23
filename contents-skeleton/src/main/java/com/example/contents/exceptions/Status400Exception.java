package com.example.contents.exceptions;

public class Status400Exception extends RuntimeException{ // 실행하면서 발견되는 오류, 언제 발생하는지 모르는 오류
    public Status400Exception (String message) {
        super(message);
    }
}
