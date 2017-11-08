package com.my.es;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ESClientFactory {
    public static final String CLUSTER_NAME = "";
    private static final String IP = "192.168.200.9";
    private static final int PORT = 9300;  //端口
    private ESClientFactory(){}

    private static Settings settings = Settings
            .settingsBuilder()
            .put("cluster.name", CLUSTER_NAME)
            .put("client.transport.sniff", true)
            .build();
    //创建私有对象
    private static TransportClient client;

    static {
        try {
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(IP), PORT));
//            client.prepareIndex()
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public static synchronized TransportClient getTransportClient(){
        return client;
    }
    //为集群添加新的节点
    public static synchronized void addNode(String name){
        try {
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(name),9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    //删除集群中的某个节点
    public static synchronized void removeNode(String name){
        try {
            client.removeTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(name), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        String index="school";
        String type="class";
        SearchResponse response=ESClientFactory.getTransportClient().prepareSearch(index)//设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(type)//设置type, 这个在建立索引的时候同时设置了, 或者可以使用head工具查看
//                .setQuery(QueryBuilders.matchQuery("age", "32")) //在这里"message"是要查询的field,"Accept"是要查询的内容
                .setFrom(0)
                .setSize(10)
                .setExplain(true)
                .execute()
                .actionGet();
        for(SearchHit hit:response.getHits()){

            System.out.println("--------------------------------"+hit.getId());
            System.out.println(hit.getSourceAsString());
        }
    }
}