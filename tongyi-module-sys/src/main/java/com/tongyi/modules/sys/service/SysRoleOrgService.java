package com.tongyi.modules.sys.service;

import com.tongyi.core.IService;
import com.tongyi.modules.sys.entity.SysRoleOrgEntity;

import java.util.List;


/**
 * 角色与机构对应关系
 *
 * @author 林佛权
 */
public interface SysRoleOrgService extends IService<SysRoleOrgEntity> {

    /**
     * 保存或更新
     *
     * @param roleId    角色ID
     * @param orgNoList orgNoList
     */
    void saveOrUpdate(String roleId, List<String> orgNoList);

    /**
     * 根据角色ID，获取机构ID列表
     *
     * @param roleId 角色ID
     * @return List
     */
    List<String> queryOrgNoList(String roleId);

    /**
     * 根据用户ID获取权限机构列表
     *
     * @param userId 用户Id
     * @return String
     */
    String queryOrgNoListByUserId(String userId);
}
