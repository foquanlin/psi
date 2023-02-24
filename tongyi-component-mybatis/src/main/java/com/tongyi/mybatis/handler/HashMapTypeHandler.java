package com.tongyi.mybatis.handler;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.gson.Gson;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeReference;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@MappedTypes(value = {HashMap.class})
public class HashMapTypeHandler extends BaseTypeHandler<HashMap<String,Object>> {
    private Gson gson = new Gson();

    protected TypeReference<HashMap<String,Object>> specificType() {
        return new TypeReference<HashMap<String,Object>>() {
        };
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, HashMap<String,Object> parameter, JdbcType jdbcType) throws SQLException {
        String content = (parameter== null || parameter.size()==0) ? null : gson.toJson(parameter);
        ps.setString(i, content);
    }

    @Override
    public HashMap<String,Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.getListByJsonArrayString(rs.getString(columnName));
    }

    @Override
    public HashMap<String,Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.getListByJsonArrayString(rs.getString(columnIndex));
    }

    @Override
    public HashMap<String,Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.getListByJsonArrayString(cs.getString(columnIndex));
    }

    private HashMap<String,Object> getListByJsonArrayString(String content) {
        return StringUtils.isBlank(content) ? new HashMap<>() : gson.fromJson(content, this.specificType().getRawType());
    }

}
