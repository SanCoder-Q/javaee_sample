package com.tw.core;

/**
 * Created by SanCoder on 7/9/15.
 */
public class User{
    private int id;
    private String name;
    private int gender;
    private String email;
    private int age;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender == 1 ? "男" : "女";
    }

    public int getGenderNum() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
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
}
