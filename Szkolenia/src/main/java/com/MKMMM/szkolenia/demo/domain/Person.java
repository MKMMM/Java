package com.MKMMM.szkolenia.demo.domain;

public class Person {

    private String name;
    private String yob;

    private Person sibling;

    public Person(String name, String yob) {
        this.name = name;
        this.yob = yob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYob() {
        return yob;
    }

    public void setYob(String yob) {
        this.yob = yob;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", yob='" + yob + '\'' +
                ", sibling=" + sibling +
                '}';
    }
}
