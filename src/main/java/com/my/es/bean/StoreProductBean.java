package com.my.es.bean;


import java.io.Serializable;
import java.util.List;

public class StoreProductBean extends BaseBean implements Serializable {
    private String name;
    private String storeCode;
    private float listPrice;
    private float offerPrice;
//    分类
    private String catalog;
//    品牌
    private String brand;
//    产地
    private String origin;
//    成分
    private List<String> compositions;
//    库存
    private long stock;

    public StoreProductBean(){
        super("store","product");
    }

    public StoreProductBean(String storeCode,String name, String catalog,float listPrice, float offerPrice, String brand, String origin, List<String> compositions) {
        super("store","product");
        this.storeCode=storeCode;
        this.name = name;
        this.catalog=catalog;
        this.listPrice = listPrice;
        this.offerPrice = offerPrice;
        this.brand = brand;
        this.origin = origin;
        this.compositions = compositions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getListPrice() {
        return listPrice;
    }

    public void setListPrice(float listPrice) {
        this.listPrice = listPrice;
    }

    public float getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(float offerPrice) {
        this.offerPrice = offerPrice;
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

    public List<String> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<String> compositions) {
        this.compositions = compositions;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }
}
