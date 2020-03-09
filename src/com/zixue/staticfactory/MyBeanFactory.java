package com.zixue.staticfactory;

import com.zixue.springdemo.PersonDao;
import com.zixue.springdemo.PersonDaoImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 在Spring中，可以通过静态工厂方法创建Bean实例
 * 这需要提供一个静态工厂方法创建Bean的实例
* */
public class MyBeanFactory {

    //创建Bean实例的静态工厂方法
    public static Person2 createBean() {
        return new Person2();
    }

    //jdk和cglib是实现AOP手动代理的两种方式
    //但Spring有自己的创建AOP代理的方式，使用ProxyFactoryBean类生成代理对象；
    //也可以在Spring中使用AspectJ开发AOP,基于XML和注解两种方式；

    //jdk动态代理
    //  jdk代理无法摆脱仅支持interface代理的命运，
    //  它必须要实现一个或多个接口，对于没有实现接口的类来说无法使用jdk代理
    //模拟Spring IoC通过调用getBean方法创建实例
    public static PersonDao getBean() {
        //准备目标类
        final PersonDao personDao = new PersonDaoImpl();

        //创建切面类实例
        final MyAspect myAspect = new MyAspect();

        //使用代理类进行增强
        return (PersonDao) Proxy.newProxyInstance(
                MyBeanFactory.class.getClassLoader(),
                personDao.getClass().getInterfaces(),
                new InvocationHandler() {
                    //实现了InvocationHandler接口的调用处理对象(实现了InvocationHandler接口的对象)
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        myAspect.myBefore();
                        Object obj = method.invoke(personDao, args);
                        myAspect.myAfter();
                        return obj;
                    }
                }
        );
    }

    //cglib代理
    //支持持对类进行代理
    public static PersonDao getCglibBean() {
        final PersonDao personDao = new PersonDaoImpl();

        final MyAspect myAspect = new MyAspect();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(personDao.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                myAspect.myBefore();
                Object obj = method.invoke(personDao, objects);
                myAspect.myAfter();
                return obj;
            }
        });

        //创建代理类
        PersonDao personDaoProxy = (PersonDao) enhancer.create();
        return personDaoProxy;
    }
}