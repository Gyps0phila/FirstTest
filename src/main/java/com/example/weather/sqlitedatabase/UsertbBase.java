package com.example.weather.sqlitedatabase;

/**
 * Created by Gypsophila on 2016/2/4.
 */
public class UsertbBase {
    private int _id;
    private String name;
    private int age;
    private String sex;

    public UsertbBase(int _id, int age, String name, String sex) {
        this._id = _id;
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
