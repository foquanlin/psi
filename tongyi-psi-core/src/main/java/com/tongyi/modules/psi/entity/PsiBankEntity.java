/*
 * 项目名称:项目名称
 * 类名称:PsiBankEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 银行账户实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@Data
@TableName(value = "psi_bank",autoResultMap = true)
public class PsiBankEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 默认
     */
    private Boolean defaulted;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行账号
     */
    private String accountNo;
    /**
     * 备注
     */
    private String memo;
    /**
     * 支行
     */
    private String bankSubname;
}
