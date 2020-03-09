package com.zixue.springdemo;

public class PersonDaoImpl implements PersonDao{
    @Override
    public void add(String s) {
        System.out.println(s+"add()执行了。。。");
    }

    @Override
    public void add() {
        System.out.println("执行add()方法。。。");
    }

    @Override
    public void delete() {
        System.out.println("执行delete()方法。。。");
    }

    @Override
    public void modify() {
        System.out.println("执行modify()方法。。。");
    }

    @Override
    public void query() {
        System.out.println("执行query()方法。。。");
    }
}
