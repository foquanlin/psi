/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsSpecEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:27
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:27
 */
@Data
@TableName(value = "psi_goods_spec",autoResultMap = true)
public class PsiGoodsSpecEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 商品
     */
    private String goodsId;
    /**
     * 规格名称
     */
    private String specName;
    /**
     * 规格值
     */
    private String specValue;
}
