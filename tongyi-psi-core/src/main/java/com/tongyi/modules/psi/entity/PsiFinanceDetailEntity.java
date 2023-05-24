/*
 * 项目名称:进销存
 * 类名称:PsiFinanceDetailEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Time;

/**
 * 非销售明细实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 */
@Data
@TableName(value = "psi_finance_detail",autoResultMap = true)
public class PsiFinanceDetailEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 主id
     */
    private String fid;
    /**
     * 创建人
     */
    private String createUid;
    /**
     * 日期
     */
    private Date createDate;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 备注
     */
    private String memo;
}
