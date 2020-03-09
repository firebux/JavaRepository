package com.zixue.staticfactory;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

//
//Spring标准方式创建aop代理
// 切面类
//
public class MyAspect implements MethodInterceptor {
    public void myBefore() {
        System.out.println("Before...");
    }

    public void myAfter() {
        System.out.println("After...");
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("方法执行之前");

        //执行目标方法
        Object obj = methodInvocation.proceed();

        System.out.println("方法执行之后");

        return obj;
    }
}
