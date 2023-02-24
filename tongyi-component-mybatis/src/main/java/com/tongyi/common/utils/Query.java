/*
 * 项目名称:tongyi-component
 * 类名称:Query.java
 * 包名称:com.tongyi.common.utils
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.common.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author 林佛权
 */
public class Query<T> extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 当前页码
     */
    private int currPage = 1;
    /**
     * 每页条数
     */
    private int limit = 10;

    private static final String ASC = "asc";

    public Query(int page,int limit,Map<String, Object> params) {
        this.currPage = page;
        this.limit = limit;
        init(params);
    }
    @Deprecated
    public Query(Map<String, Object> params) {

        String strPage = "page";
        String strLimit = "limit";
        this.putAll(params);

        //分页参数
        if (params.get(strPage) != null) {
            currPage = Integer.parseInt(params.get("page").toString());
        }
        if (params.get(strLimit) != null) {
            limit = Integer.parseInt(params.get("limit").toString());
        }
        init(params);
    }
    private void init(Map<String, Object> params){
        this.put("offset", (currPage - 1) * limit);
        this.put("page", currPage);
        this.put("limit", limit);

        String sidx = (String) params.get("sidx");
        //默认升序
        Boolean asc = true;
        if (!StringUtils.isNullOrEmpty(params.get(ASC))) {
            asc = (Boolean) params.get("asc");
        }
        //mybatis-plus分页
        this.page = new Page<T>(currPage, limit);

        //排序
        if (StringUtils.isNotBlank(sidx)) {
            if (asc) {
                this.page.addOrder(OrderItem.asc(sidx));
            } else {
                this.page.addOrder(OrderItem.desc(sidx));
            }
        }
    }
    public Page<T> getPage() {
        return page;
    }

    public int getCurrPage() {
        return currPage;
    }

    public int getLimit() {
        return limit;
    }
}
