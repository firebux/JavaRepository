package com.zixue.annotation;

import org.springframework.stereotype.Repository;

//@Repository("annoPersonDao")
@Repository
public class PersonDaoImpl implements PersonDao {
    @Override
    public void add() {
        System.out.println("PersonDaoImpl执行");
    }
}
