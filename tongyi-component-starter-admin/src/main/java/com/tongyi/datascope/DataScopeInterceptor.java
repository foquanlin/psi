/*
 * 项目名称:tongyi-component
 * 类名称:DataScopeInterceptor.java
 * 包名称:com.tongyi.datascope
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/14 09:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.datascope;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.tongyi.common.utils.ShiroUtils;
import com.tongyi.common.utils.StringUtils;
import com.tongyi.modules.sys.SysConstant;
import com.tongyi.modules.sys.entity.SysUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * @author 林佛权
 * <p>
 * mybatis 数据权限拦截器
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DataScopeInterceptor implements Interceptor {
    private static final String orderby = "order by " ;
    private static final String groupby = "group by ";
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
//        this.sqlParser(metaObject);

        // 不是SELECT操作直接返回
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            return invocation.proceed();
        }

        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        StringBuilder filterSql = new StringBuilder(boundSql.getSql());
        Object parameterObject = boundSql.getParameterObject();

        //参数中DataScope类型的参数
        DataScope dataScope = findDataScopeObject(parameterObject);

        if (dataScope == null) {
            return invocation.proceed();
        } else {
            SysUserEntity user = ShiroUtils.getUserEntity();
            if (null != user) {
                //如果不是超级管理员，则只能查询本机构及子机构数据
                if (!SysConstant.SUPER_ADMIN.equals(user.getUserId())) {
                    String userAlias = dataScope.getUserAlias();
                    String orgAlias = dataScope.getOrgAlias();
                    String alias = dataScope.getOrgNos();
                    boolean self = dataScope.getSelf();
                    StringBuilder sb = new StringBuilder();
                    String sql = filterSql.toString().toLowerCase();
                    if (sql.indexOf("where")==-1){
                        sb.append(" where 1=1");
                    }
                    if (StringUtils.isNotBlank(alias)) {
                        sb.append(" and (").append(orgAlias).append(" in (").append(alias).append(")");
                        if (self) {
                            if (StringUtils.isNotBlank(userAlias)) {
                                sb.append(" or ").append(userAlias).append("='").append(user.getUserId()).append("' ");
                            }
                        }
                        sb.append(" ) ");
                    } else if (self) {
                        if (StringUtils.isNotBlank(userAlias)) {
                            sb.append(" and ").append(userAlias).append("='").append(user.getUserId()).append("' ");
                        }
                    }
                    int start = sql.lastIndexOf("limit");
                    if (sql.lastIndexOf(orderby) !=-1  && sql.lastIndexOf(orderby) < start) {
                        start = sql.lastIndexOf(orderby);
                    }
                    if (sql.lastIndexOf(groupby) !=-1  &&  sql.lastIndexOf(groupby) < start) {
                        start = sql.lastIndexOf(groupby);
                    }

                    if (start>=0){
                        filterSql.insert(start,sb,0,sb.length());
                    }else{
                        filterSql.append(sb);
                    }
                }
                metaObject.setValue("delegate.boundSql.sql", filterSql.toString());
            }
            return invocation.proceed();
        }
    }

    /**
     * 生成拦截对象的代理
     *
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    /**
     * mybatis配置的属性
     *
     * @param properties mybatis配置的属性
     */
    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 查找参数是否包括DataScope对象
     *
     * @param parameterObj 参数列表
     * @return DataScope
     */
    private DataScope findDataScopeObject(Object parameterObj) {
        if (parameterObj instanceof DataScope) {
            return (DataScope) parameterObj;
        } else if (parameterObj instanceof Map) {
            for (Object val : ((Map<?, ?>) parameterObj).values()) {
                if (val instanceof DataScope) {
                    return (DataScope) val;
                } else {
                    if (val instanceof Map) {
                        for (Object v : ((Map<?, ?>) val).values()) {
                            if (v instanceof DataScope) {
                                return (DataScope) v;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
