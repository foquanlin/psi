/*
 * 项目名称:platform-plus
 * 类名称:SysOrgServiceImpl.java
 * 包名称:com.platform.modules.sys.service.impl
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-21 11:29:22        李鹏军     初版做成
 *
 * Copyright (c) 2018-2019 微同软件
 */
package com.platform.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.utils.StringUtils;
import com.platform.modules.sys.dao.SysOrgDao;
import com.platform.modules.sys.entity.SysOrgEntity;
import com.platform.modules.sys.service.SysOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 组织机构Service实现类
 *
 * @author 李鹏军
 * @date 2019-01-21 11:29:22
 */
@Service("sysOrgService")
public class SysOrgServiceImpl extends ServiceImpl<SysOrgDao, SysOrgEntity> implements SysOrgService {

    @Override
    public List<SysOrgEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysOrgEntity sysOrg) {

        String parentNo = sysOrg.getParentNo();

        String maxId = baseMapper.queryMaxIdByParentNo(parentNo);
        String orgNo = StringUtils.addOne(parentNo, maxId);
        sysOrg.setOrgNo(orgNo);

        int orgType = getOrgType(orgNo);
        sysOrg.setOrgType(orgType);
        sysOrg.setCreateTime(new Date());

        baseMapper.insert(sysOrg);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysOrgEntity sysOrg) {
        String orgNo = sysOrg.getOrgNo();

        int orgType = getOrgType(orgNo);
        sysOrg.setOrgType(orgType);

        this.updateById(sysOrg);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String orgNo) {
        this.removeById(orgNo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] orgNos) {
        this.removeByIds(Arrays.asList(orgNos));
    }

    @Override
    public List<SysOrgEntity> queryListByOrgNo(String orgNo) {
        return baseMapper.selectChildrensByOrgNo(orgNo);
    }

    private int getOrgType(String orgNo) {
        int two = 2;
        int four = 4;
        int six = 6;
        int egight = 8;
        int ten = 10;
        int level = 0;

        if (orgNo.length() == two) {
            level = 1;
        } else if (orgNo.length() == four) {
            level = 2;
        } else if (orgNo.length() == six) {
            level = 3;
        } else if (orgNo.length() == egight) {
            level = 4;
        } else if (orgNo.length() == ten) {
            level = 5;
        }

        return level;
    }
}
