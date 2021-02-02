package com.tongyi.modules.swaggerbootstrapui.models;

import io.swagger.models.Swagger;
import org.springframework.beans.BeanUtils;

/**
 * @author 林佛权
 */
public class SwaggerExt extends Swagger {

    protected SwaggerBootstrapUi swaggerBootstrapUi;

    public SwaggerBootstrapUi getSwaggerBootstrapUi() {
        return swaggerBootstrapUi;
    }

    public void setSwaggerBootstrapUi(SwaggerBootstrapUi swaggerBootstrapUi) {
        this.swaggerBootstrapUi = swaggerBootstrapUi;
    }

    public SwaggerExt(Swagger swagger) {
        BeanUtils.copyProperties(swagger, this);
    }
}
