package com.zixue.annotation;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//@Service("annoPersonService")
@Service
public class PersonServiceImpl implements PersonService{

//    @Resource(name="annoPersonDao")
    @Resource
//    @Autowired
    private PersonDao personDao;

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void add() {
        personDao.add();
        System.out.println("PersonServiceImpl执行。。。");
    }
}
