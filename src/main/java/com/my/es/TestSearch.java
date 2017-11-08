package com.my.es;

import com.alibaba.fastjson.JSON;
import com.my.es.bean.SchoolClassBean;
import com.my.es.bean.StoreProductBean;
import com.my.es.util.SchoolClassUtil;
import com.my.es.util.StoreProductUtil;
import com.my.es.vo.SchoolClassSearchVO;

import java.util.Arrays;
import java.util.List;

public class TestSearch {

    public  static void main(String []args){
//        SchoolClassUtil sc=new SchoolClassUtil();
//        SchoolClassSearchVO vo=new SchoolClassSearchVO();
//        // vo.setFirst_name("Douglas");
//        vo.setAbout("like");
//        List<SchoolClassBean> results=sc.execSearch(vo, SchoolClassBean.class);


        StoreProductUtil spUtil=new StoreProductUtil();
//
        StoreProductBean storeProductBean01=new StoreProductBean("01","apple 6s plus","3C",5888F,5288F,"apple","America",Arrays.asList((new String[]{"aluminum","plastic"})));
        storeProductBean01.setId("10001");
//
//        StoreProductBean storeProductBean02=new StoreProductBean("galaxy s7 edge","mobile",5666F,5066F,"Samsung ","South Korea",Arrays.asList((new String[]{"iron","plastic"})));
        System.out.println("create : "+spUtil.createOneEntity(storeProductBean01));
//        System.out.println("create : " + spUtil.createOneEntity(storeProductBean02));


//        StoreProductBean storeProductBean =spUtil.searchById("10001");

//        System.out.println(JSON.toJSONString(storeProductBean));


    }

}
