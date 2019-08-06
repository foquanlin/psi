package com.platform.common.utils;

/**
 * @author 李鹏军
 */

public enum SeckillStatEnum {
    /**
     * 秒杀状态
     */
    MUCH(2, "哎呦喂，人也太多了，请稍后！"),
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀时间已结束"),
    NOT_TIME(-1, "未到秒杀时间，请稍后重试"),
    GOODS_END(-2, "商品已抢完");

    private int state;
    private String info;

    SeckillStatEnum(int state, String info) {
        this.state = state;
        this.info = info;
    }

    public int getState() {
        return state;
    }


    public String getInfo() {
        return info;
    }


    public static SeckillStatEnum stateOf(int index) {
        for (SeckillStatEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
