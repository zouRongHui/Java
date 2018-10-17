package org.rone.study.java.entity;

import java.util.Objects;

/**
 * 实体类，用于其他模块使用
 */
public class User {
    private String name;
    private String sex;
    private Integer age;
    private String job;
    private String address;

    public User() {}

    public User(String name, String sex, Integer age, String job, String address) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.job = job;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(age, user.age) &&
                Objects.equals(job, user.job) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, sex, age, job, address);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
