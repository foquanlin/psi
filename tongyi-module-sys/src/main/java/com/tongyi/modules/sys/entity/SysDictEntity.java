/*
 * 项目名称:tongyi-component
 * 类名称:SysDictEntity.java
 * 包名称:com.tongyi.modules.sys.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-15 11:42:20        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tongyi.common.validator.group.AddGroup;
import com.tongyi.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 数据字典实体
 * 表名 sys_dict
 *
 * @author 林佛权
 */
@Data
@TableName("sys_dict")
public class SysDictEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 所属分组ID
     */
    @NotBlank(message = "所属分组不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String groupId;
    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    /**
     * 字典值
     */
    @NotBlank(message = "字典值不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String value;
    /**
     * 排序号
     */
    private Integer sort;
    /**
     * 状态码
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private String code;
}
