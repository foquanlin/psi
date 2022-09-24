package com.tongyi.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeReference;

import java.util.HashMap;
import java.util.List;

@MappedTypes(value = {List.class})
public class ListHashMapTypeHandler extends ListTypeHandler<HashMap>  {
    @Override
    protected TypeReference<List<HashMap>> specificType() {
        return new TypeReference<List<HashMap>>() {
        };
    }
}
