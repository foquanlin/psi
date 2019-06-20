package com.platform.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author 李鹏军
 */
public class JsonUtils {
    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(obj);
    }
}
