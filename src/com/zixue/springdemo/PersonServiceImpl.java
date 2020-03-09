package com.zixue.springdemo;

public class PersonServiceImpl implements PersonService {

    private PersonDao personDao;

//    //通过 构造方法 注入
//    public PersonServiceImpl(PersonDao personDao) {
//        this.personDao = personDao;
//    }

    //通过 setter方法 注入
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void addPerson() {
        personDao.add("setter注入");
        System.out.println("addPerson() 执行完毕。。。");
    }
}
