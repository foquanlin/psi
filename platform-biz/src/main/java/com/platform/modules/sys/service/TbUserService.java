/*
 * 项目名称:platform-boot
 * 类名称:UserService.java
 * 包名称:com.platform.modules.app.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.modules.sys.entity.TbUserEntity;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import java.util.List;
import java.util.Map;

/**
 * 用户service
 *
 * @author 李鹏军
 */
public interface TbUserService extends IService<TbUserEntity> {


    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<TbUserEntity> queryAll(Map<String, Object> params);

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return Page
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 新增
     *
     * @param mallUser
     * @return 新增结果
     */
    boolean add(TbUserEntity mallUser);

    /**
     * 根据主键更新
     *
     * @param mallUser
     * @return 更新结果
     */
    boolean update(TbUserEntity mallUser);

    /**
     * 根据主键删除
     *
     * @param id id
     * @return 删除结果
     */
    boolean delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids ids
     * @return 删除结果
     */
    boolean deleteBatch(String[] ids);

    /**
     * queryByMobile
     *
     * @param mobile 手机号
     * @return UserEntity
     */
    TbUserEntity queryByMobile(String mobile);

    /**
     * 登录
     *
     * @param mobile   手机号
     * @param password 密码
     * @return String
     */
    String loginByMobile(String mobile, String password);

    /**
     * 根据openId获取用户
     *
     * @param openId openId
     * @return UserEntity
     */
    TbUserEntity selectByOpenId(String openId);

    /**
     * 新增或者修改
     *
     * @param user user
     * @return UserEntity
     */
    TbUserEntity saveOrUpdateByOpenId(WxMpUser user);
}
