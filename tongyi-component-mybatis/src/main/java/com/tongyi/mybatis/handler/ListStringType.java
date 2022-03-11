package com.tongyi.mybatis.handler;

import com.tongyi.xbuilder.entity.EnumEntity;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeReference;

import java.util.List;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2022-03-11
 */
@MappedTypes(value = {List.class})
public class ListStringType extends ListTypeHandler<String> {
    @Override
    protected TypeReference<List<String>> specificType() {
        return new TypeReference<List<String>>() {
        };
    }
}
