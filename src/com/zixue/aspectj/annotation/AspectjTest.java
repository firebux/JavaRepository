package com.zixue.aspectj.annotation;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectjTest {
    @Test
    public void test() {

        String xpath = "com/zixue/aspectj/annotation/aspectjContext.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(xpath);

        TestDao testDao = (TestDao) applicationContext.getBean("testDao");
        testDao.add();
    }
}
