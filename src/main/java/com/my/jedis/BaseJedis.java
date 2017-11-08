package com.my.jedis;

import redis.clients.jedis.JedisCluster;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ccc016 on 2016/6/8.
 */
public class BaseJedis<T> {

    private JedisCluster jedisCluster = MyJedisFactory.FETCH_REDIS_CONNECTION();

    private T getT() throws InstantiationException, IllegalAccessException {
        Type sType = getClass().getGenericSuperclass();
        Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
        Class<T> mTClass = (Class<T>) (generics[0]);
        return mTClass.newInstance();
    }


    private T buildT(Map<String, String> resultMap) {

        try {
            if (resultMap != null && resultMap.size() > 0) {
                T result = this.getT();
                Class clazz = result.getClass();
                Method[] methods = clazz.getMethods();
                for (String attributeName : resultMap.keySet()) {
                    for (Method oneMethod : methods) {
                        if (oneMethod.getName().equalsIgnoreCase("set" + attributeName)) {
                            oneMethod.invoke(result, resultMap.get(attributeName));
                            break;
                        }
                    }

                }
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public T find(String key) {
        return this.buildT(jedisCluster.hgetAll(key));
    }

    /**
     * 从一个序列中，获得所有数据
     * @param seqkey   存储同类型散列ID的列表名称
     * @return
     */
    public List<T> findAllFromSeq(String seqkey){
        List<T>resultList=new ArrayList<T>();
        List<String> allKeys= jedisCluster.lrange(seqkey, 0, -1);
        if(allKeys!=null && allKeys.size()>0){
            for(String oneKey:allKeys){
                resultList.add(this.find(oneKey));
            }
        }
        return resultList;
    }

    /**
     * 从一个序列中，获得所有数据,但序列中只存了标示，需要前缀来拼接
     * @param seqkey   存储同类型散列ID的列表名称
     * @return
     */
    public List<T> findAllFromSeqWithProfix(String seqkey,String profix){
        List<T>resultList=new ArrayList<T>();
        List<String> allKeys= jedisCluster.lrange(seqkey, 0, -1);
        if(allKeys!=null && allKeys.size()>0){
            for(String oneKey:allKeys){
                resultList.add(this.find(profix+oneKey));
            }
        }
        return resultList;
    }

    /**
     *
     * @param value  Bean
     * @param key    散列ID
     * @param seqkey  存储同类型散列ID的列表名称
     * @return
     */
    public boolean set(T value, String key,String seqkey) {
        try {
            Class clazz = value.getClass();
            Method[] methods = clazz.getMethods();
            Map<String, String> paramMap = new HashMap<String, String>();
            for (Method oneMethod : methods) {
                if (oneMethod.getName().startsWith("get")) {
                    Object oneAttrValue = oneMethod.invoke(value);
                    if(oneAttrValue!=null && oneAttrValue instanceof String){
                        paramMap.put(oneMethod.getName().replaceFirst("get", "").toLowerCase(),oneAttrValue.toString());
                    }
                }
            }

            if(paramMap.size()>0){
                jedisCluster.hmset(key,paramMap);
            }
            if(seqkey!=null && !"".equals(seqkey.trim())){
                List<String> allKeys= jedisCluster.lrange(seqkey, 0, -1);
                if(allKeys==null || allKeys.size()==0 || !allKeys.contains(key)){
                    jedisCluster.lpush(seqkey,key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }
}
