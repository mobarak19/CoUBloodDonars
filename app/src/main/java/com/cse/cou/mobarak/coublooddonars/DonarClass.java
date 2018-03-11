package com.cse.cou.mobarak.coublooddonars;

/**
 * Created by Mobarak on 4/27/2017.
 */

public class DonarClass {

    String name;
    String email;
    String age;
    String phone;
    String password;
    String blood_group;


    public DonarClass(String name, String email, String age, String phone, String password, String blood_group) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.password = password;
        this.blood_group = blood_group;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
