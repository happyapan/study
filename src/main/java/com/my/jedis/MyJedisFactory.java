package com.my.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;

//日后再实现Connect Pool
public class MyJedisFactory {
    private static final HashSet clusterNodes=new HashSet<HostAndPort>();
    private static final JedisPoolConfig config = new JedisPoolConfig();
    private MyJedisFactory(){

    }
    static {
        clusterNodes.add(new HostAndPort("192.168.100.111", 7000));
        clusterNodes.add(new HostAndPort("192.168.100.111", 7001));
        clusterNodes.add(new HostAndPort("192.168.100.111", 7002));

        config.setMaxTotal(1000);
        config.setMaxIdle(100);
        config.setTestOnBorrow(true);
    }
    public static JedisCluster FETCH_REDIS_CONNECTION(){
         return  new JedisCluster(clusterNodes, 5000, config);
    }
}
