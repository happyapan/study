package com.my.silver;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.net.URL;
import java.net.URLConnection;

public class RealPrice {
    public static void main(String[] args) {
        while(true){
            try{
                read();
                Thread.sleep(5000);
            }catch(Exception e){

            }
        }
    }

    public static void read(){
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = "http://fa.163.com/interfaces/ngxcache/priceinfo/getRealTimePrice.do?partnerId=njs&goodsId=AG&v=1470894240804";
            URL realUrl = new URL(urlNameString);

            URLConnection connection = realUrl.openConnection();

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            RealPriceBean price=JSON.parseObject(connection.getInputStream(), RealPriceBean.class);
            System.out.println(price.getRet().getSynTime().substring(8)+"    "+price.getRet().getNewPrice() +"    "+price.getRet().getRaiseLoss()+"    "+price.getRet().getUpRate() +"    "+price.getRet().getOpenPrice() +"    "+price.getRet().getHighPrice() + "    "+price.getRet().getLowerPrice());

        } catch (Exception e) {
//            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
//                e2.printStackTrace();
            }
        }
    }

}
