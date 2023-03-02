package com.tongyi.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.dao.SysRoleOrgDao;
import com.tongyi.modules.sys.entity.SysRoleOrgEntity;
import com.tongyi.modules.sys.service.SysRoleOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * 角色与机构对应关系
 *
 * @author 林佛权
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
    public SysRoleOrgEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<SysRoleOrgEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<SysRoleOrgEntity> listPage(int current, int size, Map<String, Object> params) {
        Page<SysRoleOrgEntity> page = new Query<SysRoleOrgEntity>(current,size,params).getPage();
        List<SysRoleOrgEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<SysRoleOrgEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    public void execute(Serializable id, Map<String, Object> params, ModuleExecute<SysRoleOrgEntity, Map<String, Object>, Void> fun) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(fun);
        SysRoleOrgEntity entity = this.getById(id);
        this.execute(entity,params,fun);
    }

    @Override
    public void execute(SysRoleOrgEntity entity, Map<String, Object> params, ModuleExecute<SysRoleOrgEntity, Map<String, Object>, Void> fun) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(fun);
        fun.apply(entity,params);
    }

    @Override
    public void execute(SysRoleOrgEntity entity, Map<String, Object> params, ModuleExecute<SysRoleOrgEntity, Map<String, Object>, Void>... funs) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(funs);
        Arrays.stream(funs).forEach(fun->{
            fun.apply(entity,params);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(SysRoleOrgEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(SysRoleOrgEntity entity) {
        return super.updateById(entity);
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
}
