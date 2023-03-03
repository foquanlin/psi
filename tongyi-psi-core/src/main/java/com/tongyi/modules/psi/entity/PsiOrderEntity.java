/*
 * 项目名称:项目名称
 * 类名称:PsiOrderEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tongyi.common.utils.StringUtils;
import com.tongyi.modules.sys.entity.SysUserEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.sql.Time;

/**
 * 采购单实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Data
@TableName(value = "psi_order",autoResultMap = true)
public class PsiOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 订单编号
     */
    private String no;
    /**
     * 订单分类
     */
    private String catalog;
    /**
     * 订单类型
     */
    private String type;
    /**
     * 订单日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    /**
     * 客户供应商
     */
    private String orderUid;
    /**
     * 快递单号
     */
    private String expressNo;
    /**
     * 制单人
     */
    private String createUid;
    /**
     * 库存状态
     */
    private String stockStatus;
    /**
     * 发票状态
     */
    private String invoiceStatus;
    /**
     * 付款状态
     */
    private String payStatus;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 附件
     */
    private String attachmentUrls;
    /**
     * 结算金额
     */
    private BigDecimal settlementAmount;
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;
    /**
     * 责任人
     */
    private String ownerUid;
    /**
     * 已付款金额
     */
    private BigDecimal payAmount;

    @TableField(exist = false)
    private PsiSupplierEntity orderUser;
    @TableField(exist = false)
    private SysUserEntity createUser;
    @TableField(exist = false)
    private SysUserEntity ownerUser;


    public void setStatus(StockStatus stockStatus, InvoiceStatus invoiceStatus
            , PayStatus payStatus, OrderStatus status){
        this.stockStatus = stockStatus.getCode();
        this.invoiceStatus = invoiceStatus.getCode();
        this.payStatus = payStatus.getCode();
        this.status = status.getCode();
    }
    public void setInfo(String orderUid,String createUid,String ownerUid){
        this.orderUid = orderUid;
        this.createUid = createUid;
        this.ownerUid = ownerUid;
    }
    public static PsiOrderEntity newEntity(String no,Catalog catalog, Type type, StockStatus stockStatus, InvoiceStatus invoiceStatus
            , PayStatus payStatus, OrderStatus status, LocalDateTime createDate, String orderUid
            , String createUid, String ownerUid,BigDecimal orderAmount){
        PsiOrderEntity entity = new PsiOrderEntity();
        entity.no = no;
        entity.catalog = catalog.getCode();
        entity.type = type.getCode();
        entity.createDate = createDate;
        entity.orderUid = orderUid;
        entity.createUid = createUid;
        entity.stockStatus = stockStatus.getCode();
        entity.invoiceStatus = invoiceStatus.getCode();
        entity.payStatus = payStatus.getCode();
        entity.status = status.getCode();
        entity.orderAmount = orderAmount;
        entity.ownerUid = ownerUid;
        return entity;
    }
    public static PsiOrderEntity newBuyOrder(Type type, LocalDateTime createDate,BigDecimal orderAmount){
        PsiOrderEntity entity = new PsiOrderEntity();
        entity.setNo(StringUtils.generateOrderNumber("CG"));
        entity.setType(type.getCode());
        entity.setOrderAmount(orderAmount);
        entity.setCreateDate(createDate);
        return entity;
    }
    public static PsiOrderEntity newSaleOrder(Type type, LocalDateTime createDate,BigDecimal orderAmount){
        PsiOrderEntity entity = new PsiOrderEntity();
        entity.setNo(StringUtils.generateOrderNumber("XS"));
        entity.setType(type.getCode());
        entity.setOrderAmount(orderAmount);
        entity.setCreateDate(createDate);
        return entity;
    }
    /**
     * 订单分类
     */
    public enum Catalog {
        BUY("BUY","采购订单"),
        SALE("SALE","销售订单");

        private String code;
        private String name;

        Catalog(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 订单类型
     */
    public enum Type {
        ORDER("ORDER","订单"),
        REFUND("REFUND","退单");

        private String code;
        private String name;

        Type(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    /**
     * 订单状态
     */
    public enum OrderStatus {
        UNFINISH("UNFINISH","未完成"),
        FINISH("FINISH","已完成");

        private String code;
        private String name;

        OrderStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 库存状态
     */
    public enum StockStatus {
        UNFINISH("UNFINISH","未完成"),
        FINISH("FINISH","已完成");

        private String code;
        private String name;

        StockStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    /**
     * 收款状态
     */
    public enum PayStatus {
        DEBT("DEBT","有欠款"),
        PAYMENT("PAYMENT","待收款"),
        FINISH("FINISH","已完成");

        private String code;
        private String name;

        PayStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    /**
     * 发票状态
     */
    public enum InvoiceStatus {
        UNFINISH("UNFINISH","未开发票"),
        FINISH("FINISH","已开发票");

        private String code;
        private String name;

        InvoiceStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
