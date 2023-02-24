/*
 * 项目名称:tongyi-component
 * 类名称:SysOssService.java
 * 包名称:com.tongyi.modules.oss.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/17 16:21    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.oss.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tongyi.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 *
 * @author 林佛权
 */
public interface SysOssService extends IService<SysOssEntity> {

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return Page
     */
    Page queryPage(Map<String, Object> params);
}
