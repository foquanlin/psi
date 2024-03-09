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
import java.util.List;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate createDate;
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
     * 收入类型
     */
    private String typeId;
    /**
     * 已付款金额
     */
    @TableField(exist = false)
    private BigDecimal payAmount;

    @TableField(exist = false)
    private PsiSupplierEntity orderUser;
    @TableField(exist = false)
    private SysUserEntity createUser;
    @TableField(exist = false)
    private SysUserEntity ownerUser;

    @TableField(exist = false)
    private List<PsiOrderDetailEntity> details;

    public void setStockStatus(BigDecimal orderStockNum,BigDecimal orderNum) {
        if (orderStockNum.compareTo(BigDecimal.ZERO)==0){
            this.setStockStatus(PsiOrderEntity.StockStatus.UNFINISH.getCode());
        }else if (orderNum.compareTo(orderStockNum.abs())>0){
            this.setStockStatus(PsiOrderEntity.StockStatus.PARTS.getCode());
        }else{
            this.setStockStatus(PsiOrderEntity.StockStatus.FINISH.getCode());
        }
        this.setStatus();
    }
    public static PsiOrderEntity newOrder(Catalog catalog, Type type) {
        PsiOrderEntity entity = new PsiOrderEntity();
        entity.no = catalog.newNo();
        entity.catalog = catalog.getCode();
        entity.type = type.getCode();
        entity.createDate = LocalDate.now();
        entity.setStockStatus(StockStatus.UNFINISH.code);
        entity.setInvoiceStatus(InvoiceStatus.UNFINISH.code);
        entity.setPayStatus(PayStatus.PAYMENT.code);
        entity.setStatus(StockStatus.UNFINISH.code);
        return entity;
    }

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
            , PayStatus payStatus, OrderStatus status, LocalDate createDate, String orderUid
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
    public static PsiOrderEntity newBuyOrder(Type type, LocalDate createDate,BigDecimal orderAmount,String expressNo,String ownerUid,String createUid){
        PsiOrderEntity entity = new PsiOrderEntity();
        entity.setCatalog(Catalog.BUY.getCode());
        entity.setNo(Catalog.BUY.newNo());
        entity.setType(type.getCode());
        entity.setOrderAmount(orderAmount);
        entity.setCreateDate(createDate);
        entity.setExpressNo(expressNo);
        entity.setOwnerUid(ownerUid);
        entity.setCreateUid(createUid);
        entity.setStockStatus(StockStatus.UNFINISH.code);
        entity.setInvoiceStatus(InvoiceStatus.UNFINISH.code);
        entity.setPayStatus(PayStatus.PAYMENT.code);
        entity.setStatus(StockStatus.UNFINISH.code);
        return entity;
    }
    public static PsiOrderEntity newBuyOrder(Type type){
        PsiOrderEntity entity = new PsiOrderEntity();
        entity.setCatalog(Catalog.BUY.getCode());
        entity.setNo(Catalog.BUY.newNo());
        entity.setType(type.getCode());
        entity.setStockStatus(StockStatus.UNFINISH.code);
        entity.setInvoiceStatus(InvoiceStatus.UNFINISH.code);
        entity.setPayStatus(PayStatus.PAYMENT.code);
        entity.setStatus(StockStatus.UNFINISH.code);
        entity.setOrderAmount(BigDecimal.ZERO);
        entity.setPayAmount(BigDecimal.ZERO);
        entity.setSettlementAmount(BigDecimal.ZERO);
        return entity;
    }
    public static PsiOrderEntity newSaleOrder(Type type, LocalDate createDate,BigDecimal orderAmount){
        PsiOrderEntity entity = new PsiOrderEntity();
        entity.setNo(Catalog.SALE.newNo());
        entity.setType(type.getCode());
        entity.setOrderAmount(orderAmount);
        entity.setCreateDate(createDate);
        return entity;
    }
    public PsiOrderAmountEntity newOrderAmount(String bankId,BigDecimal amount){
        PsiOrderAmountEntity entity = PsiOrderAmountEntity.newEntity(this,bankId,amount);
        return entity;
    }
    public PsiStockEntity.Catalog getStockCatalog(){
        return PsiOrderEntity.Catalog.valueOf(catalog).stockCatalog();
    }
    public PsiStockEntity.Type getStockType(){
        return PsiOrderEntity.Catalog.valueOf(catalog).getStockType(PsiOrderEntity.Type.valueOf(type));
    }
    public PsiStockEntity.Type getPayType(){
        return PsiOrderEntity.Catalog.valueOf(catalog).getStockType(PsiOrderEntity.Type.valueOf(type));
    }
    public void setPayStatus(String status) {
        this.payStatus = status;
    }

    public void setPayStatus(BigDecimal total) {
        if (BigDecimal.ZERO.compareTo(total) == 0){
            this.payStatus = PayStatus.PAYMENT.getCode();
        }else if (orderAmount.compareTo(total) > 0){
            this.payStatus = PayStatus.DEBT.getCode();
        }else {
            this.payStatus = PayStatus.FINISH.getCode();
        }
        this.setStatus();
    }
    public void setInvoiceStatus(String status){
        this.invoiceStatus = InvoiceStatus.valueOf(status).code;
        this.setStatus();
    }
    private void setStatus(){
        this.status = (PayStatus.FINISH.equals(this.payStatus) && StockStatus.FINISH.equals(this.stockStatus) && InvoiceStatus.FINISH.equals(this.invoiceStatus))?OrderStatus.FINISH.code:OrderStatus.UNFINISH.code;
    }
    /**
     * 订单分类
     */
    public enum Catalog {
        BUY("BUY","采购订单"),
        SALE("SALE","销售订单"),
        IN("IN","非销售收入"),
        OUT("OUT","非采购支出");

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

        /**
         * 根据订单类型,生成订单号
         * @return
         */
        public String newNo(){// 根据订单分类生成不同订单号
            if (this == BUY) {
                return StringUtils.generateOrderNumber("CG");
            }
            if (this == SALE){
                return StringUtils.generateOrderNumber("XS");
            }
            return null;
        }

        /**
         * 根据订单类型,取得出入库分类
         * @return
         */
        public PsiStockEntity.Catalog stockCatalog(){
            if (this == BUY) {
                return PsiStockEntity.Catalog.CAIGOU;
            }
            if (this == SALE) {
                return PsiStockEntity.Catalog.XIAOSHOU;
            }
            if (this == IN) {
                return PsiStockEntity.Catalog.NONE;
            }
            if (this == OUT) {
                return PsiStockEntity.Catalog.NONE;
            }
            return null;
        }

        /**
         * 根据订单类型取得出入库类型
         * @param type
         * @return
         */
        public PsiStockEntity.Type getStockType(PsiOrderEntity.Type type){
            if (this == BUY && Type.ORDER == type) {
                return PsiStockEntity.Type.IN;
            }else if (this == BUY && Type.REFUND == type) {
                return PsiStockEntity.Type.OUT;
            }else if (this == SALE && Type.ORDER == type) {
                return PsiStockEntity.Type.OUT;
            }else if (this == SALE && Type.REFUND == type) {
                return PsiStockEntity.Type.IN;
            }
            return null;
        }

        /**
         * 根据订单类型取得订单首付款类型
         * @param type
         * @return
         */
        public PsiOrderAmountEntity.Type getPayType(PsiOrderEntity.Type type){
            if (this == BUY && Type.ORDER == type) {
                return PsiOrderAmountEntity.Type.PAY;
            }else if (this == BUY && Type.REFUND == type) {
                return PsiOrderAmountEntity.Type.RECEIPTS;
            }else if (this == SALE && Type.ORDER == type) {
                return PsiOrderAmountEntity.Type.PAY.RECEIPTS;
            }else if (this == SALE && Type.REFUND == type) {
                return PsiOrderAmountEntity.Type.PAY;
            }
            return null;
        }
    }

    /**
     * 订单类型
     */
    public enum Type {
        NONE("NONE","无"),
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

        public boolean equals(String code){
            if (StringUtils.isBlank(code))
                return false;
            return this.code.equals(code);
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

        public boolean equals(String code){
            if (StringUtils.isBlank(code))
                return false;
            return this.code.equals(code);
        }

    }

    /**
     * 库存状态
     */
    public enum StockStatus {
        UNFINISH("UNFINISH","未完成"),
        PARTS("PARTS","部分完成"),
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

        public boolean equals(String code){
            if (StringUtils.isBlank(code))
                return false;
            return this.code.equals(code);
        }

    }

    /**
     * 收款状态
     */
    public enum PayStatus {
        PAYMENT("PAYMENT","待收款"),
        DEBT("DEBT","有欠款"),
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
        public boolean equals(String code){
            if (StringUtils.isBlank(code))
                return false;
            return this.code.equals(code);
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

        public boolean equals(String code){
            if (StringUtils.isBlank(code))
                return false;
            return this.code.equals(code);
        }
    }

}
