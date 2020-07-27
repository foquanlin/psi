package com.platform.common.handler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class JSONObjectTypeHandler extends BaseTypeHandler<JsonObject> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonObject object, JdbcType jdbcType) throws SQLException {
        if (null==object){
            ps.setString(i,"");
            return;
        }
        ps.setString(i,object.toString());
    }

    @Override
    public JsonObject getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        if (null==resultSet.getString(columnName)){
            return null;
        }
        return JsonParser.parseString(resultSet.getString(columnName)).getAsJsonObject();
    }

    @Override
    public JsonObject getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        if (null==resultSet.getString(columnIndex)){
            return null;
        }
        return JsonParser.parseString(resultSet.getString(columnIndex)).getAsJsonObject();
    }

    @Override
    public JsonObject getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        if (null==callableStatement.getString(columnIndex)){
            return null;
        }
        return JsonParser.parseString(callableStatement.getString(columnIndex)).getAsJsonObject();
    }
}
