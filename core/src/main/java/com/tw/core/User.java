package com.tw.core;


import javax.annotation.Generated;
import javax.persistence.*;
import javax.persistence.metamodel.CollectionAttribute;
import java.io.Serializable;

/**
 * Created by SanCoder on 7/9/15.
 */

@Entity
@Table(name="users")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private int gender;

    @Column(name = "email")
    private String email;

    @Column
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
