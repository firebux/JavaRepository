package com.zixue;

import com.zixue.annotation.PersonAction;
import com.zixue.springdemo.PersonDao;
import com.zixue.springdemo.PersonDaoImpl;
import com.zixue.springdemo.PersonService;
import com.zixue.staticfactory.MyBeanFactory;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstTest {
    @Test
    public void test() throws InterruptedException {
        String str = "asl;kjdfajfoi${asdf1.ABC.adf1}akl${asdf2.ABC.adf2}jfl${asdf3.ABD.adf3}akj${asdf3.AXX.adf3}";
//        String pattern = "\\$\\{(?<prefix>.+?)\\.ABC\\.(?<suffix>.+?)\\}";
//        String pattern = ".+?(?<xx>\\$\\{(.+?\\.ABD\\..+?)\\}).+?";
        String pattern = "(\\$\\{(?:(?!\\.).)+?\\.ABD\\..+?\\})";
//        String pattern =  "\\$\\{(?<substr>.+?)\\}";
//        String pattern =  "(\\$\\{.+?\\})";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);

        List<String> list = new ArrayList<>();
        while (m.find()) {
            list.add(m.group());
//            list.add(m.group("prefix"));
//            list.add(m.group("suffix"));
        }
        System.out.println(list.toString());


        String xmlPath = "applicationContext.xml"; //此路径为FirstTest和xml同级
        //从classpath路径中寻找xml配置文件
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

        //指定 配置文件 绝对路径
        FileSystemXmlApplicationContext applicationContext =
                new FileSystemXmlApplicationContext("E:\\space\\Java\\HelloWorld\\src\\"+xmlPath);

        //通过Bean获取实例，不在通过new
        PersonDao personDao = (PersonDao) applicationContext.getBean("personDao");
        personDao.add("Get Object From Bean");

        PersonDao t = new PersonDaoImpl();
        t.add("New Object");

        Thread.sleep(3000);

        PersonService personService = (PersonService) applicationContext.getBean("personService");
        personService.addPerson();

        //静态工厂创建Bean
        System.out.println(applicationContext.getBean("person2"));
        System.out.println(applicationContext.getBean("person2"));

        System.out.println(applicationContext.getBean("personSetter"));
        System.out.println(applicationContext.getBean("personConstruct"));


        //annotation
        PersonAction personAction = (PersonAction) applicationContext.getBean("personAction");
        personAction.add();

        System.out.println("============================AOP 手动创建代理 JDK Proxy==========================");
        PersonDao personJDK = MyBeanFactory.getBean();
        personJDK.add();
        personJDK.delete();
        personJDK.modify();
        personJDK.query();

//        System.out.println("============================AOP 手动创建代理 CGLIB Proxy==========================");
//        PersonDao personCglib = MyBeanFactory.getCglibBean();
//        personCglib.add();
//        personCglib.delete();
//        personCglib.modify();
//        personCglib.query();

        try{
            System.out.println("============================反 射==========================");
            //这是不是反射？
            Class.forName("com.zixue.springdemo.PersonDao").getMethod("delete").invoke(t, null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        byte[] data = ProxyGenerator.generateProxyClass("ProxyPerson", new Class[] {PersonDao.class});
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("ProxyPerson.class");
            out.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
