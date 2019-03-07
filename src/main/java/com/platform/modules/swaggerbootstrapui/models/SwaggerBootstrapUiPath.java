package com.platform.modules.swaggerbootstrapui.models;

/**
 * @author 李鹏军
 */
public class SwaggerBootstrapUiPath {

    private String path;

    private String method;

    private Integer order;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public SwaggerBootstrapUiPath(String path, String method, Integer order) {
        this.path = path;
        this.method = method;
        this.order = order;
    }

    public SwaggerBootstrapUiPath() {
    }
}
