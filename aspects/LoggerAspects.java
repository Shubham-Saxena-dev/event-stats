package com.hf.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@Aspect
@Configuration
public class LoggerAspects {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.hf.service.*.*(..))")
    public void logMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Method executed {} with params {} : ", joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        log.info("Value returned {}", result);
    }
}
