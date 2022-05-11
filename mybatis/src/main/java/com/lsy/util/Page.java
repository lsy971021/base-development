package com.lsy.util;

import lombok.Data;

import java.util.List;

/**
 * mybatis分页工具类
 */
@Data
public class Page {
    //一共有多少条数据
    private Integer datacount;
    //每页显示多少条数据
    private Integer showdata;
    //一共分成多少页
    private Integer pagecount;
    //当前是第几页
    private Integer pageIndex;
    //当前页面显示的细心集合
    private List<?> list;

    public Integer getPagecount() {
        return this.datacount%this.showdata==0?this.datacount/this.showdata:this.datacount/this.showdata+1;
    }
}
