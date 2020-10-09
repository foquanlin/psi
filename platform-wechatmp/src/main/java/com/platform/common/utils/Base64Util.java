package com.platform.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.common.xss.SQLFilter;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

/**
 * 作者: @author 林佛权 <br>
 * 时间: 2017-08-11 16:17<br>
 * 描述: Base64 <br>
 */
public class Base64Util {
    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String encode(String str) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] b = str.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(b);
    }

    /**
     * 解密
     *
     * @param s
     * @return
     */
    public static String decode(String s) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(s), StandardCharsets.UTF_8);
    }

    /**
     * 查询参数
     * @author Mark sunlightcs@gmail.com
     */
    public static class Query<T> {

        public IPage<T> getPage(Map<String, Object> params) {
            return this.getPage(params, null, false);
        }

        public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
            //分页参数
            long curPage = 1;
            long limit = 10;

            if (params.get(Constant.PAGE) != null) {
                curPage = Long.parseLong((String) params.get(Constant.PAGE));
            }
            if (params.get(Constant.LIMIT) != null) {
                limit = Long.parseLong((String) params.get(Constant.LIMIT));
            }

            //分页对象
            Page<T> page = new Page<>(curPage, limit);

            //分页参数
            params.put(Constant.PAGE, page);

            //排序字段
            //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
            String orderField = SQLFilter.sqlInject((String) params.get(Constant.ORDER_FIELD));
            String order = (String) params.get(Constant.ORDER);


            //前端字段排序
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(orderField) && org.apache.commons.lang3.StringUtils.isNotEmpty(order)) {
                if (Constant.ASC.equalsIgnoreCase(order)) {
                    return page.addOrder(OrderItem.asc(orderField));
                } else {
                    return page.addOrder(OrderItem.desc(orderField));
                }
            }

            //没有排序字段，则不排序
            if (StringUtils.isBlank(defaultOrderField)) {
                return page;
            }

            //默认排序
            if (isAsc) {
                page.addOrder(OrderItem.asc(defaultOrderField));
            } else {
                page.addOrder(OrderItem.desc(defaultOrderField));
            }

            return page;
        }
    }

}
