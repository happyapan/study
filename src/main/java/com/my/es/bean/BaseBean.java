package com.my.es.bean;


public class BaseBean {
    private String index; //ES index
    private String type; //ES type
    private String id; //ES id
    private String comment;

    public BaseBean(String index,String type){
        this.index=index;
        this.type=type;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
