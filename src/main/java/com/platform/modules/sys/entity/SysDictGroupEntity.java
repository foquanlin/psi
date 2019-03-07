/*
 * 项目名称:platform-plus
 * 类名称:SysDictGroupEntity.java
 * 包名称:com.platform.modules.sys.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-15 11:42:20        李鹏军     初版做成
 *
 * Copyright (c) 2018-2019 微同软件
 */
package com.platform.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.platform.common.validator.group.AddGroup;
import com.platform.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典分组实体
 * 表名 sys_dict_group
 *
 * @author 李鹏军
 * @date 2019-01-15 11:42:20
 */
@Data
@TableName("SYS_DICT_GROUP")
public class SysDictGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private String id;
    /**
     * 分组编码
     */
    @NotBlank(message = "分组编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String code;
    /**
     * 分组名称
     */
    @NotBlank(message = "分组名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;
}
