package com.my.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ccc016 on 2016/6/1.
 */
public class ProductVO  implements IBaseVO {

    private String id;
    private String name;
    private String price;
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

//    public String getIdentityId() {
//        return this.id;
//    }
//    public Map toMap(){
//        Map values=new HashMap();
//        values.put("id",this.getId());
//        values.put("name",this.getName());
//        values.put("price",this.getPrice());
//        values.put("desc",this.getDesc());
//        return values;
//    }
}
