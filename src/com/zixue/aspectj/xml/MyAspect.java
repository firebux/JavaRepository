package com.zixue.aspectj.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//切面类
//切面中的几种通知，在实际使用中可以自由选择哪一种实现，不一定全用的上
public class MyAspect {
    //before
    public void myBefore(JoinPoint joinPoint) {
        System.out.println("Target:"+joinPoint.getTarget());
        System.out.println("--------------before----------------");
        System.out.println("方法名称："+joinPoint.getSignature().getName());
    }

    //after
    public void myAfterReturning(JoinPoint joinPoint) {
        System.out.println("--------------returning----------------");
        System.out.println("后置通知，方法名称："+joinPoint.getSignature().getName());
    }

    //环绕通知
    //几种通知中，最强大的一种通知，
    //它可以决定是否继续执行连接点方法 或者 跳过连接点方法直接在环绕通知里返回或者抛出异常
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("--------------around----------------");
        System.out.println("环绕开始");
        Object object = proceedingJoinPoint.proceed(); //执行当前 连接点方法
        System.out.println("环绕结束");
        return object;
    }

    //异常通知
    public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("异常通知：出错了"+e.getMessage());
    }

    //最终通知
    public void myAfter() {
        System.out.println("--------------after----------------");
        System.out.println("最终通知");
    }
}
