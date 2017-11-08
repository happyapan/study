package com.my.jedis;


import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args){
        Jedis jedis=  new Jedis("192.168.200.8",6382);
        jedis.set("test:name","Jeff");
        System.out.println(jedis.get("test:name"));



    }
}
