package com.my;

/**
 * Created by ccc016 on 2016/6/8.
 */
public class StudyConstants {


    //LIST-----------------------------------------
    //商品
    public static final String SEQ_ALL_PRODUCT="seq:product";
    //用户
    public static final String SEQ_ALL_USER="seq:user";
    //订单
    public static final String SEQ_ORDER="seq:order";

    //订单任务列表,下单完成后，用于后台任务处理，解耦订单模块
    public static final String SEQ_TASK_ORDER="seq:taskorder";



    //PREFIX---------------------------------------------
    public static final String PREFIX_PRODUCT="product:";
    public static final String PREFIX_USER="user:";
    public static final String PREFIX_ORDER="order:";

    //用户购物车，使用时后面加用户Account
    public static final String PROFIX_SHOPPING_CART="shoppingCart:";
    //用户订单列表，使用时后面加用户Account
    public static final String PROFIX_ORDER_LIST="orderlist:";


    //INDEX------------------------------------------------
    // 序列
    public static final String INDEX_USER="index:user";
    public static final String INDEX_ORDER="index:order";
    public static final String INDEX_PRODUCT="index:product";




}
