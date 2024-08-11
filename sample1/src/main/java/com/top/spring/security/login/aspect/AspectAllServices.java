package com.top.spring.security.login.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectAllServices {

    @Before("execution(* com.top.spring.security.login.service.*.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        // Advice logic here
        System.out.println("Aboudeh Aspect " );
        System.out.println("Before method: " + joinPoint.getSignature().getName());
    }
}