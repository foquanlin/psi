package com.platform.common.handler;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class JSONArrayTypeHandler extends BaseTypeHandler<JsonArray> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonArray array, JdbcType jdbcType) throws SQLException {
//        System.out.println(array.toString());
//        System.out.println(array.getAsString());
        ps.setString(i,array.toString());
    }

    @Override
    public JsonArray getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String val = "[]";
        if (null!=resultSet.getString(columnName)){
            val = resultSet.getString(columnName);
        }
        return JsonParser.parseString(val).getAsJsonArray();
    }

    @Override
    public JsonArray getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String val = "[]";
        if (null!=resultSet.getString(resultSet.getString(columnIndex))){
            val = resultSet.getString(resultSet.getString(columnIndex));
        }
        return JsonParser.parseString(val).getAsJsonArray();
    }

    @Override
    public JsonArray getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String val = "[]";
        if (null!=callableStatement.getString(columnIndex)){
            val = callableStatement.getString(columnIndex);
        }
        return JsonParser.parseString(val).getAsJsonArray();
    }
}
