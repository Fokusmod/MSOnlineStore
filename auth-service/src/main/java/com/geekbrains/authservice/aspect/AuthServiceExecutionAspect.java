package com.geekbrains.authservice.aspect;

import com.geekbrains.authservice.service.AuthStatisticService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Component
@RequiredArgsConstructor
public class AuthServiceExecutionAspect {

    private final AuthStatisticService authStatisticService;

    @Around("@annotation(com.geekbrains.apiservice.annotation.ExecutionTime)")
    public Object setTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        MethodSignature ms = (MethodSignature) proceedingJoinPoint.getSignature();
        String method = ms.getMethod().getName();

        long start = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();
        Long result = System.currentTimeMillis() - start;

        authStatisticService.saveStatistic(className,method,result);
        return object;
    }

}
