package com.example.aop.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 어디에 붙을 건지
@Retention(RetentionPolicy.RUNTIME) // 언제까지 유지될 건지
public @interface LogArguments {

}
