package com.zixue.aspectj.annotation;

import org.springframework.stereotype.Repository;

@Repository("testDao")
public class TestDaoImpl implements TestDao {
    @Override
    public void add() {
        System.out.println("TestDaoImpl add()执行。。。");
    }
}
