package com.zixue.aspectj.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* com.zixue.aspectj.annotation.*.* (..))")
    private void myPointCut() {
        //该方法必须是private，且无参无返回值
        //名称自定义
    }

    @Before("myPointCut()")
    public void myBefore(JoinPoint joinPoint) {
        System.out.println("Before");
    }

    @Around("myPointCut()")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("--Around : before--");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("--Around : after--");
        return proceed;
    }

    @After("myPointCut()")
    public void myAfter(JoinPoint joinPoint){
        System.out.println("After");
    }
}
