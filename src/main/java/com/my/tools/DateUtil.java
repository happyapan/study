package com.my.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ccc016 on 2016/6/15.
 */
public class DateUtil {
    private DateUtil(){ }

    public static String  GET_CURRENT_TIME(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date());
    }
}
