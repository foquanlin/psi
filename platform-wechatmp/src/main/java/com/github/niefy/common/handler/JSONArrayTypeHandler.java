package com.github.niefy.common.handler;

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
        ps.setString(i,array.getAsString());
    }

    @Override
    public JsonArray getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return JsonParser.parseString(resultSet.getString(columnName)).getAsJsonArray();
    }

    @Override
    public JsonArray getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return JsonParser.parseString(resultSet.getString(columnIndex)).getAsJsonArray();
    }

    @Override
    public JsonArray getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return JsonParser.parseString(callableStatement.getString(columnIndex)).getAsJsonArray();
    }
}
