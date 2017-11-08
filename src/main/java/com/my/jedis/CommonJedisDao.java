package com.my.jedis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

@Component("commonJedisDao")
public class CommonJedisDao {
    private JedisCluster jedisCluster = MyJedisFactory.FETCH_REDIS_CONNECTION();

    /**
     * GET the next value of sequence
     * @param indexKey
     * @return
     */
    public String incr(String indexKey){
        return ""+ this.jedisCluster.incr(indexKey).longValue();
    }

    public boolean isExist(String redisKey){
        boolean exist=false;
        try{
            String type=this.jedisCluster.type(redisKey);
            if(type==null || "".equals(type) || !"none".equals(type.toLowerCase())){
                exist=true;
            }
        }catch(Exception e ){
            //do nothing
        }
        return exist;
    }

    public Long lpush(String redisKey, String... values){
        return this.jedisCluster.lpush(redisKey,values);
    }
    public Long lrem(String redisKey, String value){
        return this.jedisCluster.lrem(redisKey, 1, value);
    }
    public Long del(String redisKey){
        return this.jedisCluster.del(redisKey);
    }

    public String rpop(String redisKey){
        return this.jedisCluster.rpop(redisKey);
    }

    public String get(String redisKey){
        return this.jedisCluster.get(redisKey);
    }
    public String set(String redisKey,String value){
        return this.jedisCluster.set(redisKey, value);
    }
}
