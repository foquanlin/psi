/*
 * 项目名称:项目名称
 * 类名称:SysNationEntity.java
 * 包名称:com.tongyi.modules.sys.entity
 * @author 惠州市酷天科技有限公司
 * @date 2021-02-02 20:05:49
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 民族实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2021-02-02 20:05:49
 */
@Data
@TableName("SYS_NATION")
public class SysNationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    private String code;
    /**
     * 
     */
    private String name;
}
