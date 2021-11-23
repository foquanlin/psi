/*
 * 项目名称:tongyi-component
 * 类名称:SysDictGroupService.java
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
import com.tongyi.modules.sys.entity.SysDictGroupEntity;

/**
 * 数据字典分组Service接口
 *
 * @author 林佛权
 */
public interface SysDictGroupService extends IService<SysDictGroupEntity> {

    SysDictGroupEntity getByCode(String code);
}
