package com.my.es.vo;


public class StoreProductSearchVO extends BaseSearchVO {

    private String name;
    private String brand;
    private String origin;

    public StoreProductSearchVO() {
        super("store", "product");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
