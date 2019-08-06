/*
 * 项目名称:platform-plus
 * 类名称:Constant.java
 * 包名称:com.platform.common.utils
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.common.utils;

/**
 * 常量
 *
 * @author 李鹏军
 */
public class Constant {
    public static final String WEBSOCKET_USER = "WEBSOCKET_USER";

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    /**
     * 商城订单
     */
    public static final Integer NORMAL_ORDER = 1;
    /**
     * 店铺自提订单
     */
    public static final Integer SHOPS_ORDER = 2;
    /**
     * 秒杀订单
     */
    public static final Integer SECKILL_ORDER = 3;
    /**
     * 超级管理员ID
     */
    public static final String SUPER_ADMIN = "1";
    /**
     * 超级管理员所属机构
     */
    public static final String SUPER_ADMIN_ORG = "01";

    public static final String DEFAULT_PW = "888888";

    /**
     * 6小时后过期
     */
    public static final int EXPIRE = 3600 * 6;

    /**
     * 下单支付过期时间，单位分
     */
    public static final String ORDER_EXPIRE = "ORDER_EXPIRE";

    /**
     * 云存储配置KEY
     */
    public final static String CLOUD_STORAGE_CONFIG_KEY = "CLOUD_STORAGE_CONFIG_KEY";

    /**
     * 短信配置KEY
     */
    public final static String SMS_CONFIG_KEY = "SMS_CONFIG_KEY";
    /**
     * 邮费
     */
    public final static String SHIPPING_FEE = "SHIPPING_FEE";
    /**
     * 短信
     */
    public final static String PRE_SMS = "PRE_SMS:";

    /**
     * 权限前缀
     */
    public static final String SESSION = "SESSION:";

    /**
     * 系统缓存前缀
     */
    public static final String SYS_CACHE = "SYS_CACHE:";

    /**
     * 业务系统缓存前缀
     */
    public static final String MTM_CACHE = "MTM_CACHE:";

    public static final String STR_ZERO = "0";
    public static final String STR_ONE = "1";
    public static final String STR_TWO = "2";
    public static final String STR_THREE = "3";
    public static final String STR_FOUR = "4";

    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;

    /**
     * 未关注
     */
    public static final int UNSUBSCRIBE = 0;
    /**
     * 关注
     */
    public static final int SUBSCRIBE = 1;
    /**
     * 空字符串
     */
    public static final String BLANK = "";
    /**
     * 斜杠
     */
    public static final String SLASH = "/";
    /**
     * 逗号
     */
    public static final String COMMA = ",";
    /**
     * 句号
     */
    public static final String DOT = ".";
    /**
     * 冒号
     */
    public static final String COLON = ":";
    /**
     * 下划线
     */
    public static final String UNDERSCORE = "_";
    /**
     * 换行符
     */
    public static final String LINE_BREAK = "\\r\\n";

    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";

    public static final String BPMN20 = ".bpmn20.xml";

    public static final String IMAGE = "image";

    public static final String XML = "xml";
    public static final String PNG = "png";
    public static final String BAR = "bar";
    public static final String ZIP = "zip";
    public static final String BPMN = "bpmn";

    /**
     * 系统邮件签名
     */
    public static final String SIGNATURE_STR = "<br><font color='red'>-------------------------------------------------------------------<br>以上内容为邮件系统自动发送，请勿直接回复。</font>";

    /**
     * 系统自动邮件
     */
    public static final int SYS_SEND = 0;
    /**
     * 操作人主动邮件
     */
    public static final int USER_SEND = 1;

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3),
        /**
         * 服务器存储
         */
        DISCK(4),
        /**
         * FastDFS
         */
        FAST_DFS(5);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 订单状态
     */
    public enum OrderStatus {

        /**
         * 秒杀成功，待下单补充信息
         */
        DXD(-1),
        /**
         * 0:订单创建成功等待付款
         */
        DFK(0),
        /**
         * 100:已过期
         */
        YGQ(100),
        /**
         * 101:订单已取消
         */
        YQX(101),
        /**
         * 102:订单已删除
         */
        YSC(102),
        /**
         * 201:订单已付款,等待发货
         */
        YFK(201),
        /**
         * 300:订单已发货
         */
        YFH(300),
        /**
         * 301:用户确认收货
         */
        QRSH(301),
        /**
         * 401:没发货，退款
         */
        TK(401),
        /**
         * 402:已收货，退款退货
         */
        THTK(402);

        private Integer value;

        OrderStatus(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 发货状态，商品配送情况
     */
    public enum ShippingStatus {
        /**
         * 1:未发货
         */
        WFH(1),
        /**
         * 2:已发货
         */
        YFH(2),
        /**
         * 3:已收货
         */
        YSH(3),
        /**
         * 4:退货
         */
        TH(4);

        private Integer value;

        ShippingStatus(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 付款状态
     */
    public enum PayStatus {
        /**
         * 1:未付款
         */
        WFK(1),
        /**
         * 2:付款中
         */
        FKZ(2),
        /**
         * 3:已付款
         */
        YFK(3),
        /**
         * 4:退款
         */
        TK(4);

        private Integer value;

        PayStatus(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 用户下单来源类型
     */
    public enum FromType {
        /**
         * 1:微信小程序
         */
        MA(1),
        /**
         * 2:微信公众号
         */
        MP(2),
        /**
         * 3:app
         */
        APP(3),
        /**
         * 4:H5
         */
        H5(4),
        /**
         * 支付宝小程序
         */
        ALI(5),
        /**
         * QQ小程序
         */
        QQ(6);

        private Integer value;

        FromType(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}
