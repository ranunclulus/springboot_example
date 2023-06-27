package com.example.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
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

    // this: 클래스의 구현체 instance 지정
    // @Before("this(com.example.aop.controller.AopController)")

    // within: 클래스 또는 패키지 지정
    // @Before("within(com.example.aop.controller..*)")
    // @Before("within(com.example.aop.controller.AopController)")

    // @annotation: 어노테이션 지정
    @Before("@annotation(com.example.aop.aspect.LogArguments)")
    public void logParameter(JoinPoint joinPoint) {
        // 실행된 메소드의 정보를 담는 객체
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();
        log.info(methodSignature.getName());
        Object[] arguments = joinPoint.getArgs();

        // 인자가 없을 때
        if (arguments.length == 0) {
            log.info("no args");
        }
        for (Object argument : arguments) {
            log.info("argument: [{}]", arguments);
        }
    }

    // 어떤 메소드가 실행되는데 걸리는 시간을 기록하고 싶을 때
    // @LogExecutionTime 이 붙은 메소드의 실행 시간 기록
    @Around("@annotation(com.example.aop.aspect.LogExecutionTime)")
    public Object logExecutionTime(
            // Advice 내에서 대상 JoinPoint가 실행되도록
            // 요구할 수 있다
            ProceedingJoinPoint joinPoint
    ) throws Throwable {
        long startTime = System.currentTimeMillis();

        // jointPoint.proceed(): joinpoint에 해당하는 메소드
        // 진행해 주세요
        Object proceed = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        // 실제로 추가하고 싶은 기능
        log.info("{} executed in: {}ms",
                joinPoint.getSignature(), endTime - startTime);
        return proceed;
    }
}
