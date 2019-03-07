/*
 * 项目名称:platform-plus
 * 类名称:DataScope.java
 * 包名称:com.platform.datascope
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/14 09:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.datascope;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
 * @author 李鹏军
 * <p>
 * 数据权限查询参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataScope extends HashMap {

    /**
     * SQL中数据创建用户（通常传入CREATE_USER_ID）的别名
     */
    private String userAlias = "T.CREATE_USER_ID";

    /**
     * SQL中数据CREATE_USER_ORG_NO的别名
     */
    private String orgAlias = "T.CREATE_USER_ORG_NO";

    /**
     * 具体的数据范围
     */
    private String orgNos;

    /**
     * true：没有机构数据权限，也能查询本人数据
     */
    private Boolean self = true;
}
