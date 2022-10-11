/*
 * 项目名称:项目名称
 * 类名称:PsiAllocationEntity.java
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
import java.util.Date;
import java.sql.Time;

/**
 * 调拨单实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@Data
@TableName(value = "psi_allocation",autoResultMap = true)
public class PsiAllocationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 单号
     */
    private String no;
    /**
     * 出库仓库
     */
    private String outWarehouseId;
    /**
     * 入库仓库
     */
    private String inWarehouseId;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 备注
     */
    private String memo;
}
