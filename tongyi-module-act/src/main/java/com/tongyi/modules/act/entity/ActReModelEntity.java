/*
 * 项目名称:tongyi-component
 * 类名称:ActReModelEntity.java
 * 包名称:com.tongyi.modules.act.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 13:33:07        林佛权     初版做成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.act.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 *
 * @author 林佛权
 */
@Data
@TableName("act_re_model")
public class ActReModelEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final String BPMN20 = ".bpmn20.xml";

    public static final String IMAGE = "image";

    public static final String XML = "xml";
    public static final String PNG = "png";
    public static final String BAR = "bar";
    public static final String ZIP = "zip";
    public static final String BPMN = "bpmn";

    /**
     *
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 乐观锁版本号
     */
    private Integer rev;
    /**
     * 模型的名称
     */
    private String name;
    /**
     * 模型的关键字，流程引擎用到。
     */
    private String key;
    /**
     * 类型，用户自己对流程模型的分类。
     */
    private String category;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;
    /**
     * 版本，从1开始。
     */
    private Integer version;
    /**
     * 数据源信息，以json格式保存流程定义的信息
     */
    private String metaInfo;
    /**
     * 部署ID
     */
    private String deploymentId;
    /**
     * 编辑源值ID，是 ACT_GE_BYTEARRAY 表中的ID_值。
     */
    private String editorSourceValueId;
    /**
     * 编辑源额外值ID，是 ACT_GE_BYTEARRAY 表中的ID_值。
     */
    private String editorSourceExtraValueId;
    /**
     * TENANT_ID_
     */
    private String tenantId;

    @TableField(exist = false)
    private String description;
}
