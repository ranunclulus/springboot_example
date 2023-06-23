package com.example.contents.exceptions;

public class InvalidEmailException extends Status400Exception{
    public InvalidEmailException() {
        super("invalid email");
    }
}
