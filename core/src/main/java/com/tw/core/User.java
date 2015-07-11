package com.tw.core;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by SanCoder on 7/9/15.
 */


public class User implements Serializable {
    private int id;
    private String name;
    private int gender;
    private String email;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenderStr() {
        return gender == 1 ? "男" : "女";
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(){

    }

    public User(int id, String name, String gender, String email, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender.equals("男") ? 1 : 0;
        this.email = email;
        this.age = age;
    }

    public User(int id, String name, int gender, String email, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.age = age;
    }

    public User(String name, int gender, String email, int age) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.age = age;
    }
}
