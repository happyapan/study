package com.my.es.util;

import com.alibaba.fastjson.JSON;
import com.my.es.ESClientFactory;
import com.my.es.bean.BaseBean;
import com.my.es.vo.BaseSearchVO;
import com.my.tools.StringUtil;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @param <T> 查询Bean
 * @param <E> 实体Bean
 */

public class ESBaseUtil<T extends BaseSearchVO, E extends BaseBean> {

    //排除的查询条件列表
    private static List<String> ignoreSearchConditionList;

    /*
    查询条件中，屏蔽掉以下属性
     */
    static {
        ignoreSearchConditionList = new ArrayList<String>();
        ignoreSearchConditionList.add("getIndex");
        ignoreSearchConditionList.add("getTitgetSearchTypele");
        ignoreSearchConditionList.add("");
        ignoreSearchConditionList.add("getStart");
        ignoreSearchConditionList.add("getEnd");
        ignoreSearchConditionList.add("getClass");
    }

    private TransportClient tc = ESClientFactory.getTransportClient();


    /**
     * 创建ES数据 但现在还未屏蔽Index 和Type也存到了对象数据中，以后再完善。问题不大
     * @param entity  数据类
     * @return ID
     */
    public String createOneEntity(E entity) {
        if (!StringUtil.isEmpty(entity.getId())) {
            IndexResponse response = tc.prepareIndex(entity.getIndex(), entity.getType(), entity.getId())
                    .setSource(JSON.toJSONString(entity))
                    .get();
            return response.getId();
        } else {
            IndexResponse response = tc.prepareIndex(entity.getIndex(), entity.getType())
                    .setSource(JSON.toJSONString(entity))
                    .get();
            return response.getId();
        }
    }

    /**
     * 根据Search VO，查询
     * @param searchVO 查询VO，会根据VO中，属性存在的值进行匹配
     * @param clazz 返回List中的对应类
     * @param <K>
     * @return
     */
    public <K> List<K> execSearch(T searchVO, Class<K> clazz) {
        SearchRequestBuilder srb = this.buildSearch(searchVO);
        if (srb != null) {
            List<K> resultList = new ArrayList<K>();

            SearchResponse response = srb.execute().actionGet();
            if (response != null) {
                System.out.println("Total--------------------------------" + response.getHits().totalHits());
                for (SearchHit hit : response.getHits()) {
                    System.out.println("--------------------------------" + hit.getId());
                    System.out.println(hit.getSourceAsString());
                    resultList.add(JSON.parseObject(hit.getSourceAsString(), clazz));
                }
                return resultList;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     *
     * @param index ES
     * @param type ES
     * @param id ES
     * @param clazz ES
     * @return 返回值Bean
     */
    public Object execSearch(String index,String type ,String id, Class clazz) {
        GetResponse response = tc.prepareGet(index, type, id).get();
        if(response!=null){
            return JSON.parseObject(response.getSourceAsString(), clazz);
        }else{
            return null;
        }

    }

    /**
     * 删除--根据ID
     * @param index
     * @param type
     * @param id
     * @return
     */
    public boolean execDelete(String index,String type ,String id){
        DeleteResponse response = tc.prepareDelete(index, type, id).get();
        return response.isFound();
    }


    private SearchRequestBuilder buildSearch(T searchVO) {
        SearchRequestBuilder bulid = tc.prepareSearch(searchVO.getIndex())//设置要查询的索引(index)
                .setTypes(searchVO.getType())//设置type, 这个在建立索引的时候同时设置了, 或者可以使用head工具查看
                .setSearchType(searchVO.getSearchType())
                .setFrom(searchVO.getStart())
                .setSize(searchVO.getSize())
                .setExplain(true);

        //开始拼装查询条件
        Class clazz = searchVO.getClass();
        Method[] methods = clazz.getMethods();
        //遍历查询条件Bean，拿到所有的get方法（剔除需要忽略的），组装查询条件
        for (Method oneMethod : methods) {
            if (oneMethod.getName().startsWith("get") && !ignoreSearchConditionList.contains(oneMethod.getName())) {
                try {
                    Object returnValue = oneMethod.invoke(searchVO);
                    if (!StringUtil.isEmpty(returnValue)) {
                        if (returnValue instanceof String) {
                            bulid = bulid.setQuery(QueryBuilders.matchQuery(StringUtil.lowerCaseTheFirstLetter(oneMethod.getName().replaceFirst("get", "")), returnValue));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return bulid;
    }
}
