/*
 * 项目名称:项目名称
 * 类名称:PsiSupplierEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@Data
@TableName(value = "psi_supplier",autoResultMap = true)
public class PsiSupplierEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 联系人
     */
    private String concat;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 地址
     */
    private String address;
    /**
     * 备注
     */
    private String memo;
    /**
     * 权重
     */
    private Integer weight;
    /**
     * 状态
     */
    private String status;
    /**
     * 类型
     */
    private String type;
}
