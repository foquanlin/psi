package com.platform.modules.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.common.utils.PageUtils;
import com.platform.modules.wx.entity.MsgReplyRule;

import java.util.List;
import java.util.Map;

public interface MsgReplyRuleService extends IService<MsgReplyRule> {
    /**
     * 分页查询用户数据
     * @param params 查询参数
     * @return PageUtils 分页结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存自动回复规则
     *
     * @param msgReplyRule
     */

    @Override
    boolean save(MsgReplyRule msgReplyRule);

    /**
     * 获取所有的回复规则
     *
     * @return
     */
    List<MsgReplyRule> getRules();

    /**
     * 获取当前时段内所有有效的回复规则
     *
     * @return 有效的规则列表
     */
    List<MsgReplyRule> getValidRules();

    /**
     * 筛选符合条件的回复规则
     *
     *
     * @param appid
     * @param exactMatch 是否精确匹配
     * @param keywords   关键词
     * @return 规则列表
     */
    List<MsgReplyRule> getMatchedRules(String appid, boolean exactMatch, String keywords);
}
