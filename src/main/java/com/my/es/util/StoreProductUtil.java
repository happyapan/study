package com.my.es.util;

import com.my.es.bean.StoreProductBean;
import com.my.es.vo.StoreProductSearchVO;



public class StoreProductUtil extends ESBaseUtil<StoreProductSearchVO,StoreProductBean> {
    public StoreProductBean searchById(String id){
        Object result=super.execSearch("store","product",id,StoreProductBean.class);
        return result==null? null :(StoreProductBean)result;
    }
    public  boolean deleteById(String id){
        return super.execDelete("store","product",id);
    }

}
