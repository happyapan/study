package com.my.es.vo;


import org.elasticsearch.action.search.SearchType;

public class BaseSearchVO {
    private String index; //ES index
    private String type; //ES type
    private int start;    //ES 分页
    private int size;      //ES 分页  This limit can be set by changing the [index.max_result_window] index level parameter.]
    private SearchType searchType;

    public BaseSearchVO(String index,String type){
        this.index=index;
        this.type=type;
        start=0;
        size=100;
        searchType=SearchType.DFS_QUERY_THEN_FETCH;
        //Although the Java API defines the additional search types QUERY_AND_FETCH and DFS_QUERY_AND_FETCH, these modes are internal optimizations and should not be specified explicitly by users of the API.
    }

    public String getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

}
