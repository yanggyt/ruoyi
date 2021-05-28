package com.ruoyi.test.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Beauty {
    private String name;
    private int age;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    private double salary;

    public Beauty() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Beauty [name=" + name + ", age=" + age + ", date=" + date + ", salary=" + salary + "]";
    }

}
