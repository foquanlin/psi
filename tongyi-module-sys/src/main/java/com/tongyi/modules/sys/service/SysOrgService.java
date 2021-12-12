/*
 * 项目名称:tongyi-component
 * 类名称:SysOrgService.java
 * 包名称:com.tongyi.modules.sys.service
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-21 11:29:22        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.service;

import com.tongyi.core.IService;
import com.tongyi.modules.sys.entity.SysOrgEntity;

import java.util.List;
/**
 * 组织机构Service接口
 *
 * @author 林佛权
 */
public interface SysOrgService extends IService<SysOrgEntity> {

    /**
     * 根据OrgNo查询子机构
     *
     * @param orgNo 机构编码
     * @return List
     */
    List<SysOrgEntity> queryListByOrgNo(String orgNo);
}
