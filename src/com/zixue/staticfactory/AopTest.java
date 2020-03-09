package com.zixue.staticfactory;

import com.zixue.springdemo.PersonDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    @Test
    public void test() {

        String xmlpath = "com/zixue/staticfactory/aopContext.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlpath);

        System.out.println("=============================AOP Advice==============================");
        //创建代理对象
        PersonDao personAdvice = (PersonDao) applicationContext.getBean("personDaoProxy");
        personAdvice.add();
        personAdvice.delete();
        personAdvice.modify();
        personAdvice.query();
    }
}
