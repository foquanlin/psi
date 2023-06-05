/*
 * 项目名称:项目名称
 * 类名称:PsiOrderAmountEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 订单账目实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@Data
@TableName(value = "psi_order_amount",autoResultMap = true)
public class PsiOrderAmountEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 订单
     */
    private String orderId;
    /**
     * 日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    /**
     * 类型
     */
    private String type;

    /**
     * 银行账户
     */
    private String bankId;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 操作人
     */
    private String createUid;
    /**
     * 备注
     */
    private String memo;
    /**
     * 附件
     */
    private String attachmentUrls;
    @TableField(exist = false)
    private String bankName;

    @TableField(exist = false)
    private PsiBankEntity bank;

    public static PsiOrderAmountEntity newEntity(PsiOrderEntity module,String bankId,BigDecimal amount) {
        PsiOrderAmountEntity entity = new PsiOrderAmountEntity();
        entity.setOrderId(module.getId());
        entity.setBankId(bankId);
        entity.setAmount(amount);
        entity.setCreateDate(LocalDate.now());
        entity.setCreateUid(module.getCreateUid());
        entity.setType(module.getPayType()!=null?module.getPayType().getCode():null);
        return entity;
    }
    public static PsiOrderAmountEntity newEntity(String orderId,String createUid ,PsiOrderEntity.Type type,String bankId,BigDecimal amount) {
        PsiOrderAmountEntity entity = new PsiOrderAmountEntity();
        entity.setOrderId(orderId);
        entity.setBankId(bankId);
        entity.setAmount(amount);
        entity.setCreateDate(LocalDate.now());
        entity.setCreateUid(createUid);
        entity.setType(type.getCode());
        return entity;
    }
    public enum Type {
        PAY("PAY","付款"),
        RECEIPTS("RECEIPTS","收款");

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
}
