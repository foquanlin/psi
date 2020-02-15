/*
 * 项目名称:platform-plus
 * 类名称:ActReProcdefEntity.java
 * 包名称:com.platform.modules.act.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 09:47:54        李鹏军     初版做成
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
 * @date 2019-03-18 09:47:54
 */
@Data
@TableName("ACT_RE_PROCDEF")
public class ActReProcdefEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 流程ID，由“流程编号：流程版本号：自增长ID”组成
     */
    @TableId
    private String id;
    /**
     * 乐观锁版本号
     */
    private Integer rev;
    /**
     * 流程命名空间（该编号就是流程文件targetNamespace的属性值）
     */
    private String category;
    /**
     * 流程名称（该编号就是流程文件process元素的name属性值）
     */
    private String name;
    /**
     * 流程编号（该编号就是流程文件process元素的id属性值）
     */
    private String key;
    /**
     * 流程版本号（由程序控制，新增即为1，修改后依次加1来完成的）
     */
    private Integer version;
    /**
     * 部署编号
     */
    private String deploymentId;
    /**
     * 资源文件名称
     */
    private String resourceName;
    /**
     * 图片资源文件名称
     */
    private String dgrmResourceName;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 是否从key启动
     * start节点是否存在formKey
     * <p>
     * 0否  1是
     */
    private Integer hasStartFormKey;
    /**
     * 是否有图片预览
     */
    private Integer hasGraphicalNotation;
    /**
     * 是否挂起
     * <p>
     * 1激活 2挂起
     */
    private Integer suspensionState;
    /**
     * 所属系统
     */
    private String tenantId;
    /**
     * 工作流引擎
     */
    private String engineVersion;

    /**
     * 部署时间
     */
    @TableField(exist = false)
    private Date deployTime;
}
