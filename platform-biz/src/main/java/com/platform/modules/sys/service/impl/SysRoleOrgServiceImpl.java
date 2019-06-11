package com.platform.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.modules.sys.dao.SysRoleOrgDao;
import com.platform.modules.sys.entity.SysRoleOrgEntity;
import com.platform.modules.sys.service.SysRoleOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色与机构对应关系
 *
 * @author lipengjun
 * @date 2017年9月18日 上午9:18:38
 */
@Service("sysRoleOrgService")
public class SysRoleOrgServiceImpl extends ServiceImpl<SysRoleOrgDao, SysRoleOrgEntity> implements SysRoleOrgService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(String roleId, List<String> orgNoList) {
        //先删除角色与菜单关系
        baseMapper.deleteByRoleId(roleId);

        if (null == orgNoList || orgNoList.size() == 0) {
            return;
        }

        //保存角色与菜单关系
        List<SysRoleOrgEntity> list = new ArrayList<>(orgNoList.size());
        for (String orgNo : orgNoList) {
            SysRoleOrgEntity sysRoleMenuEntity = new SysRoleOrgEntity();
            sysRoleMenuEntity.setOrgNo(orgNo);
            sysRoleMenuEntity.setRoleId(roleId);

            list.add(sysRoleMenuEntity);
        }
        this.saveBatch(list);
    }

    @Override
    public List<String> queryOrgNoList(String roleId) {
        return baseMapper.queryOrgNoList(roleId);
    }

    @Override
    public String queryOrgNoListByUserId(String userId) {
        List<String> roleOrglist = baseMapper.queryOrgNoListByUserId(userId);
        StringBuilder roleStr = new StringBuilder();
        String alias = "";
        if (roleOrglist != null && !roleOrglist.isEmpty()) {
            for (String roleId : roleOrglist) {
                roleStr.append(",");
                roleStr.append("'");
                roleStr.append(roleId);
                roleStr.append("'");
            }
            alias = roleStr.toString().substring(1, roleStr.length());
        }
        return alias;
    }

    @Override
    public int deleteBatch(String[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}
