package com.zixue.annotation;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

//@Controller("personAction")
@Controller
public class PersonAction {

//    @Resource(name = "annoPersonService")
    @Resource
//    @Autowired
    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public void add() {
        personService.add();
        System.out.println("PersonAction 执行了。。。");
    }
}
