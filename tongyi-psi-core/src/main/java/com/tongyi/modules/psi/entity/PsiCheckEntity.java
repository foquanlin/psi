/*
 * 项目名称:项目名称
 * 类名称:PsiCheckEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
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
import java.util.Date;
import java.sql.Time;
import java.util.List;

/**
 * 盘点实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@Data
@TableName(value = "psi_check",autoResultMap = true)
public class PsiCheckEntity implements Serializable {
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
     * 仓库
     */
    private String warehouseId;
    /**
     * 盘点日期
     */
    private Date createDate;
    /**
     * 备注
     */
    private String memo;
    /**
     * 操作人
     */
    private String createUid;

    @TableField(exist = false)
    private PsiWarehouseEntity warehouse;
    @TableField(exist = false)
    private SysUserEntity createUser;

    public static PsiCheckEntity newEntity(String userId,String warehouseId, String memo) {
        PsiCheckEntity item = new PsiCheckEntity();
        item.setCreateUid(userId);
        item.setWarehouseId(warehouseId);
        item.setMemo(memo);
        item.setCreateDate(new Date());
        item.setNo(StringUtils.generateOrderNumber("PD"));
        return item;
    }
}
