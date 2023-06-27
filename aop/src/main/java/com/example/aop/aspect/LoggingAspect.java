package com.example.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j // log 추가
@Aspect // 이 클래스가 관점임을 나타냄
@Component // bean 객체로 등록
public class LoggingAspect {
    // 컨트롤러 클래스의 풀 네임
    // @Before: Advice, 실제로 실행될 코드를 나타냄
    // @Before.value: Pointcut Designator, 어느 Joint point에서 실행될 것인
    // AopController가 실행되는 시점에 로그가 작동
    @Before("this(com.example.aop.controller.AopController)")
    public void logParameter(JoinPoint joinPoint) {
        // 실행된 메소드의 정보를 담는 객체
        MethodSignature methodSignature =
                (MethodSignature)joinPoint.getSignature();
        log.info(methodSignature.getName());
        Object[] arguments = joinPoint.getArgs();

        // 인자가 없을 때
        if (arguments.length ==0) {
            log.info("no args");
        }
        for (Object argument : arguments) {
            log.info("argument: [{}]", arguments);
        }
    }

    // within: zmffotm Ehsms voz
}
