/*
 * 项目名称:platform-plus
 * 类名称:ActReModelEntity.java
 * 包名称:com.platform.modules.act.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 13:33:07        李鹏军     初版做成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.act.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 *
 * @author 李鹏军
 * @date 2019-03-18 13:33:07
 */
@Data
@TableName("ACT_RE_MODEL")
public class ActReModelEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId
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
