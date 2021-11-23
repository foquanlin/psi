/*
 * 项目名称:tongyi-component
 * 类名称:SysOrgServiceImpl.java
 * 包名称:com.tongyi.modules.sys.service.impl
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-21 11:29:22        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.common.utils.StringUtils;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.dao.SysOrgDao;
import com.tongyi.modules.sys.entity.SysOrgEntity;
import com.tongyi.modules.sys.service.SysOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 组织机构Service实现类
 *
 * @author 林佛权
 */
@Service("sysOrgService")
public class SysOrgServiceImpl extends ServiceImpl<SysOrgDao, SysOrgEntity> implements SysOrgService {

    @Override
    public SysOrgEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<SysOrgEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<SysOrgEntity> listPage(int current, int size, Map<String, Object> params) {
        Page<SysOrgEntity> page = new Query<SysOrgEntity>(current,size,params).getPage();
        List<SysOrgEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<SysOrgEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(SysOrgEntity sysOrg) {
        String parentNo = sysOrg.getParentNo();

        String maxId = baseMapper.queryMaxIdByParentNo(parentNo);
        String orgNo = StringUtils.addOne(parentNo, maxId);
        sysOrg.setOrgNo(orgNo);

        int orgType = getOrgType(orgNo);
        sysOrg.setOrgType(orgType);
        sysOrg.setCreateTime(new Date());

        return super.save(sysOrg);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(SysOrgEntity sysOrg) {
        String orgNo = sysOrg.getOrgNo();

        int orgType = getOrgType(orgNo);
        sysOrg.setOrgType(orgType);

        return super.updateById(sysOrg);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        return super.removeByIds(Arrays.asList(ids));
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
