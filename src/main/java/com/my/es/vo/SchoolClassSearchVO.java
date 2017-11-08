package com.my.es.vo;


public class SchoolClassSearchVO extends BaseSearchVO {
    private String first_name;
    private String about;

    public SchoolClassSearchVO() {
        super("school", "class");
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
