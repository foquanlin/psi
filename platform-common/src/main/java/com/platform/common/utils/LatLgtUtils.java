/*
 * 项目名称:platform-plus
 * 类名称:LatLgtUtils.java
 * 包名称:com.platform.common.utils
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算周边商家工具类
 *
 * @author 李鹏军
 */
public class LatLgtUtils {

    private static final double EARTH_RADIUS = 6378137;

    /**
     * 获取四个顶点的list
     *
     * @param lgt      经度
     * @param lat      纬度
     * @param distance 距离
     */
    public static List<Double[]> getPointsList(double lgt, double lat, double distance) {

        List<Double[]> pointsList = new ArrayList<>(4);

        double dlng = 2 * Math.asin(Math.sin(distance / (2 * EARTH_RADIUS)) / Math.cos(rad(lat)));
        dlng = degrees(dlng);

        double dlat = distance / EARTH_RADIUS;
        dlat = degrees(dlat);

        // key:纬度  value:经度
        Double[] points = new Double[]{lat + dlat, lgt - dlng};
        // 左上角的顶点
        pointsList.add(points);

        // 右上角的顶点
        points = new Double[]{lat + dlat, lgt + dlng};
        pointsList.add(points);

        // 右下角的顶点
        points = new Double[]{lat - dlat, lgt + dlng};
        pointsList.add(points);

        // 左下角的顶点
        points = new Double[]{lat - dlat, lgt - dlng};
        pointsList.add(points);

        return pointsList;

    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    private static double degrees(double d) {
        return d * (180 / Math.PI);
    }

    /**
     * 传入经纬度计算距离，单位为km,保留2位小数
     */
    public static String calDistance(float lng1, float lat1, float lng2, float lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS / 1000;
        return String.format("%.2f", s);
    }
}
