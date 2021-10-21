/*
 * 项目名称:tongyi-component
 * 类名称:SysOssServiceImpl.java
 * 包名称:com.tongyi.modules.oss.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/17 16:21    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.oss.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.oss.dao.SysOssDao;
import com.tongyi.modules.oss.entity.SysOssEntity;
import com.tongyi.modules.oss.service.SysOssService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 林佛权
 */
@Service("sysOssService")
public class SysOssServiceImpl extends ServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "t.create_date");
        params.put("asc", false);
        Page<SysOssEntity> page = new Query<SysOssEntity>(params).getPage();
        return page.setRecords(baseMapper.selectSysOssPage(page, params));
    }
}
