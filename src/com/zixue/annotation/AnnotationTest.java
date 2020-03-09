package com.zixue.annotation;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationTest {
    @Test
    public void test() {
        String path = "com/zixue/annotation/annotationContext.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);

        PersonAction personAction = (PersonAction) applicationContext.getBean("personAction");
        personAction.add();
    }
}