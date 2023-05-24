/*
 * 项目名称:进销存
 * 类名称:PsiFinanceEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
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
import java.util.Date;

/**
 * 非销售实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 */
@Data
@TableName(value = "psi_finance",autoResultMap = true)
public class PsiFinanceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 编号
     */
    private String no;
    /**
     * 类型
     */
    private String typeId;
    /**
     * 客户供应商
     */
    private String supplierId;
    /**
     * 日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date createDate;
    /**
     * 创建人
     */
    private String createUid;
    /**
     * 负责人
     */
    private String ownerUid;
    /**
     * 备注
     */
    private String memo;

    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private String createName;
    @TableField(exist = false)
    private String ownerName;
    @TableField(exist = false)
    private String supplierName;
}
