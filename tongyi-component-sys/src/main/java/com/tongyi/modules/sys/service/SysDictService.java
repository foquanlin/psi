/*
 * 项目名称:tongyi-component
 * 类名称:SysDictService.java
 * 包名称:com.tongyi.modules.sys.service
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-15 11:42:20        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.service;

import com.tongyi.core.IService;
import com.tongyi.modules.sys.entity.SysDictEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据字典Service接口
 *
 * @author 林佛权
 */
public interface SysDictService extends IService<SysDictEntity> {


    /**
     * 根据code查询数据字典
     *
     * @param params 查询参数
     * @return List
     */
    List<SysDictEntity> queryByCode(Map<String, Object> params);
}
