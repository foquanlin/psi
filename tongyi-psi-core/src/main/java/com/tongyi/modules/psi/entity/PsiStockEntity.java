/*
 * 项目名称:项目名称
 * 类名称:PsiStockEntity.java
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
import com.tongyi.common.utils.StringUtils;
import com.tongyi.modules.sys.entity.SysUserEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.util.List;

/**
 * 库存实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Data
@TableName(value = "psi_stock",autoResultMap = true)
public class PsiStockEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 客户供应商
     */
    private String supplierId;
    /**
     * 仓库
     */
    private String warehouseId;
    /**
     * 商品
     */
    private String goodsId;
    /**
     * skuid
     */
    private String skuId;
    /**
     * 关联单号
     */
    private String orderId;
    /**
     * 出入库分类
     */
    private String catalog;
    /**
     * 出入库类型
     */
    private String type;
    /**
     * 数量
     */
    private BigDecimal num;
    /**
     * 批次
     */
    private String batchNo;
    /**
     * 时间
     */
    private Date createTime;
    /**
     * 状态
     */
    private String status;
    /**
     * 平均进价
     */
    private BigDecimal costPrice;
    /**
     * 平均售价
     */
    private BigDecimal salePrice;
    /**
     * 操作人
     */
    private String createUid;

    @TableField(exist = false)
    private PsiGoodsSkuEntity sku;
    @TableField(exist = false)
    private PsiSupplierEntity supplier;
    @TableField(exist = false)
    private PsiGoodsEntity goods;
    @TableField(exist = false)
    private PsiWarehouseEntity warehouse;
    @TableField(exist = false)
    private SysUserEntity createUser;


    /**
     * 出入库类型
     */
    public enum Type {
        IN("IN","入库"),
        OUT("OUT","出库");

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
     * 出入库分类
     */
    public enum Catalog {
        TIAOZHENG("TIAOZHENG","库存调整"),
        DIAOBO("DIAOBO","库存调拨"),
        PANDIAN("PANDIAN","库存盘点"),
        DINGDAN("DINGDAN","订单"),
        XIAOSHOU("XIAOSHOU","销售");

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
    public enum Status {
        RUN("RUN","启用"),
        STOP("STOP","停用");

        private String code;
        private String name;

        Status(String code, String name) {
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
     * 生成盘单的出入库明细
     * @param userId
     * @param checkEntity
     * @return
     */
    public List<PsiStockEntity> newForCheck(String userId,PsiCheckEntity checkEntity){
        List<PsiCheckDetailEntity> details = checkEntity.getDetails();
        List<PsiStockEntity> list = new ArrayList<>();
        details.forEach(item->{
            PsiStockEntity entity = item.newCheckStock(userId,checkEntity.getNo());
            entity.setCreateUid(userId);
            entity.setOrderId(checkEntity.getNo());
            list.add(entity);
        });
        return list;
    }
}
