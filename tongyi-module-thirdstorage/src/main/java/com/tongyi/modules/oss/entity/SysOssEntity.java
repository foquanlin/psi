/*
 * 项目名称:tongyi-component
 * 类名称:SysOssEntity.java
 * 包名称:com.tongyi.modules.oss.entity
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/17 16:23    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.oss.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 *
 * @author 林佛权
 */
@Data
@TableName("sys_oss")
public class SysOssEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * URL地址
     */
    private String url;
    /**
     * 创建时间
     */
    private Date createDate;


    public static SysOssEntity newEntity(String url){
        SysOssEntity item = new SysOssEntity();
        item.setUrl(url);
        item.setCreateDate(new Date());
        return item;
    }
}
