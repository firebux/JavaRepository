package com.zixue.aspectj.xml;

import com.zixue.springdemo.PersonDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectXmlTest {
    @Test
    public void test() {
        String xmlpath = "com/zixue/aspectj/xml/aspectXmlContext.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlpath);

        PersonDao personDao = (PersonDao) applicationContext.getBean("personDao");
        personDao.modify();
    }
}
