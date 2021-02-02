/*
 * 项目名称:tongyi-component
 * 类名称:SysLogServiceImpl.java
 * 包名称:com.tongyi.modules.sys.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.sys.dao.SysLogDao;
import com.tongyi.modules.sys.entity.SysLogEntity;
import com.tongyi.modules.sys.service.SysLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 林佛权
 */
@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public IPage queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");

        Page<SysLogEntity> page = new Query<SysLogEntity>(params).getPage();

        return baseMapper.selectPage(page,
                new QueryWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key), "USER_NAME", key)
                        .or().like(StringUtils.isNotBlank(key), "OPERATION", key)
                        .orderByDesc("CREATE_TIME"));
    }
}
