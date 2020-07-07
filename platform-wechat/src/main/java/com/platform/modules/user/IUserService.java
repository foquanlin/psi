package com.platform.modules.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import java.util.List;
import java.util.Map;

public interface IUserService<T> extends IService<T> {
    T getById(String id);


    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<T> queryAll(Map<String, Object> params);

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
    boolean add(T mallUser);

    /**
     * 根据主键更新
     *
     * @param mallUser
     * @return 更新结果
     */
    boolean update(T mallUser);

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
    T queryByMobile(String mobile);

    /**
     * 登录
     *
     * @param mobile   手机号
     * @param password 密码
     * @return String
     */
    T loginByMobile(String mobile, String password);

    /**
     * 根据openId获取用户
     *
     * @param openId openId
     * @return UserEntity
     */
    T selectByOpenId(String openId);

    /**
     * 新增或者修改
     *
     * @param user user
     * @return UserEntity
     */
    T saveOrUpdateByOpenId(WxMpUser user);
}
