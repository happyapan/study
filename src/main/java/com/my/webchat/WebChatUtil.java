package com.my.webchat;

import com.google.gson.Gson;
import com.my.tools.HttpConnectionUtils;
import com.my.tools.P;
import com.my.webchat.constants.MenuConstants;

import java.util.HashMap;

/**
 * Created by ccc016 on 2017/6/10.
 */
public class WebChatUtil {

    //测试号信息
    private final static String appId = "wx07b27e5e0f4bf6e8";

    private final static String secret = "d4624c36b6795d1d99dcf0547af5443d";

    private final static String myWebChatAccount="okyPBvz_DkhGJ2y04_4y80jzczCU";

    public static String getAccessToken() {
        String accessToken = HttpConnectionUtils.httpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret);
        //        String drpAccessToken = HttpConnectionUtils.httpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx61391e1f10ee735f&secret=06a104216c64d10969d22f6cdad93442");
        Gson gson = new Gson();
        HashMap map =gson.fromJson(accessToken, HashMap.class);
        String access_token =(String)map.get("access_token");
        System.out.println("access_token---->"+access_token);
        return access_token;
    }

    public static void main(String[] args) {

        //        sendTemplateMsg();
        createMenu();
//        sendTextMsg();
        //sendNewsMsg();
     /*   generateRCode();*/
    }

    public static void sendTextMsg() {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + getAccessToken();
        String params = "{\"touser\":\""+ myWebChatAccount+"\",\"msgtype\":\"text\",\"text\": {\"content\":\"Hello\"}}";
        String returnValue = HttpConnectionUtils.httpPost(url, params);
        System.out.println(returnValue);
    }
    public static void createMenu() {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getAccessToken();
        String returnValue = HttpConnectionUtils.httpPost(url, MenuConstants.MENU);
        System.out.println(returnValue);
    }
}
