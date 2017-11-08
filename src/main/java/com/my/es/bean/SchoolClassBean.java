package com.my.es.bean;


import java.io.Serializable;
import java.util.List;

public class SchoolClassBean extends  BaseBean implements Serializable {

    public SchoolClassBean(){
        super("school","class");
    }

    public SchoolClassBean(String first_name, String last_name, int age, String about, List<String> interests) {
        super("school","class");
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.about = about;
        this.interests = interests;
    }

    private String first_name;
    private String last_name;
    private int age;
    private String about;
    private List<String> interests;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
}
