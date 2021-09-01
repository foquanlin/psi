package com.tongyi.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-04-09
 */
public class MapUtils {
    public static <K,V> Map<K,V> singletonMap(K key, V value) {
        HashMap<K,V> map = new HashMap<K,V>();
        map.put(key, value);
        return map;
    }
}
