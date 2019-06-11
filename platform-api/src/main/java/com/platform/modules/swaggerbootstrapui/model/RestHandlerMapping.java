package com.platform.modules.swaggerbootstrapui.model;

import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author 李鹏军
 */
public class RestHandlerMapping {

    private String url;

    private Class<?> beanType;

    private Method beanOfMethod;

    private Set<RequestMethod> requestMethods;

    public Set<RequestMethod> getRequestMethods() {
        return requestMethods;
    }

    public void setRequestMethods(Set<RequestMethod> requestMethods) {
        this.requestMethods = requestMethods;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Class<?> getBeanType() {
        return beanType;
    }

    public void setBeanType(Class<?> beanType) {
        this.beanType = beanType;
    }

    public Method getBeanOfMethod() {
        return beanOfMethod;
    }

    public void setBeanOfMethod(Method beanOfMethod) {
        this.beanOfMethod = beanOfMethod;
    }

    public RestHandlerMapping(String url, Class<?> beanType, Method beanOfMethod, Set<RequestMethod> requestMethods) {
        this.url = url;
        this.beanType = beanType;
        this.beanOfMethod = beanOfMethod;
        this.requestMethods = requestMethods;
    }

    public RestHandlerMapping() {
    }
}
