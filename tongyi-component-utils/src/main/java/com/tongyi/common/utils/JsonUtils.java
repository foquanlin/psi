package com.tongyi.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeMap;

/**
 * @author 林佛权
 */
public class JsonUtils {
    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(obj);
        TreeMap<String, Object> map = gson.fromJson(json, TreeMap.class);
        return gson.toJson(map);
    }

    public static <T> T fromJson(String json,Class<T> t){
        return new GsonBuilder().create().fromJson(json,t);
    }
    public static <T> T fromJson(InputStream in, Class<T> t){
        return new GsonBuilder().setPrettyPrinting().create().fromJson(new InputStreamReader(in),t);
    }
}
