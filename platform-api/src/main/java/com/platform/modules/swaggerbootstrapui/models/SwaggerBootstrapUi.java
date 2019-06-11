package com.platform.modules.swaggerbootstrapui.models;

import java.util.List;

/**
 * @author 李鹏军
 */
public class SwaggerBootstrapUi {

    /***
     * tag排序属性
     */
    protected List<SwaggerBootstrapUiTag> tagSortLists;

    /***
     * path排序
     */
    private List<SwaggerBootstrapUiPath> pathSortLists;

    public List<SwaggerBootstrapUiPath> getPathSortLists() {
        return pathSortLists;
    }

    public void setPathSortLists(List<SwaggerBootstrapUiPath> pathSortLists) {
        this.pathSortLists = pathSortLists;
    }

    public List<SwaggerBootstrapUiTag> getTagSortLists() {
        return tagSortLists;
    }

    public void setTagSortLists(List<SwaggerBootstrapUiTag> tagSortLists) {
        this.tagSortLists = tagSortLists;
    }
}
