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
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tongyi.common.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    /**
     * 备注
     */
    private String memo;

    /**
     * 入库仓库
     */
    @TableField(exist = false)
    private PsiWarehouseEntity inWarehouse;
    /**
     * 出库仓库
     */
    @TableField(exist = false)
    private PsiWarehouseEntity outWarehouse;

    public static PsiAllocationEntity newEntity(String inWarehouseId, String outWarehouseId, String memo) {
        PsiAllocationEntity entity = new PsiAllocationEntity();
        String no = StringUtils.generateOrderNumber("DB");
        entity.setInWarehouseId(inWarehouseId);
        entity.setOutWarehouseId(outWarehouseId);
        entity.setCreateDate(LocalDate.now());
        entity.setMemo(memo);
        entity.setNo(no);
        return entity;
    }
}
