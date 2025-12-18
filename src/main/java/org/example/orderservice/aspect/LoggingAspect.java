package org.example.orderservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @AfterReturning(
            pointcut = "execution(* org.example.orderservice.service.MyService.*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("LoggingAspect: Method executed successfully: " + joinPoint.getSignature().getName());
        System.out.println("LoggingAspect:Returned value: " + result);
    }
}
