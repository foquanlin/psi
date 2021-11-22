/*
 * 项目名称:tongyi-component
 * 类名称:SysSmsLogServiceImpl.java
 * 包名称:com.tongyi.modules.sys.service.impl
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-02-12 09:51:15        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.*;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.dao.SysSmsLogDao;
import com.tongyi.modules.sys.entity.SmsConfig;
import com.tongyi.modules.sys.entity.SysSmsLogEntity;
import com.tongyi.modules.sys.service.SysConfigService;
import com.tongyi.modules.sys.service.SysSmsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 短信发送日志Service实现类
 *
 * @author 林佛权
 */
@Service("sysSmsLogService")
public class SysSmsLogServiceImpl extends ServiceImpl<SysSmsLogDao, SysSmsLogEntity> implements SysSmsLogService {

    @Autowired
    private SysConfigService sysConfigService;
    
    @Override
    public SysSmsLogEntity sendSms(String userId, SysSmsLogEntity smsLog) {
        smsLog.setType(SmsUtil.TYPE);
        String result = SmsConfig.BLANK;
        //获取云存储配置信息
        SmsConfig config = sysConfigService.getConfigObject(SmsConfig.SMS_CONFIG_KEY, SmsConfig.class);
        if (StringUtils.isNullOrEmpty(config)) {
            throw new BusinessException("请先配置短信平台信息");
        }
        if (StringUtils.isNullOrEmpty(config.getName())) {
            throw new BusinessException("请先配置短信平台用户名");
        }
        if (StringUtils.isNullOrEmpty(config.getPwd())) {
            throw new BusinessException("请先配置短信平台密钥");
        }
        if (StringUtils.isNullOrEmpty(config.getSign())) {
            throw new BusinessException("请先配置短信平台签名");
        }
        /**
         * 创锐
         */
        if (config.getType() == 1) {
            try {
                /**
                 * 状态,发送编号,无效号码数,成功提交数,黑名单数和消息，无论发送的号码是多少，一个发送请求只返回一个sendid，如果响应的状态不是“0”，则只有状态和消息
                 */
                result = SmsUtil.crSendSms(config.getName(), config.getPwd(), smsLog.getMobile(), smsLog.getContent(), config.getSign(),
                        DateUtils.format(smsLog.getStime(), "yyyy-MM-dd HH:mm:ss"), smsLog.getExtno());
            } catch (Exception e) {

            }
            String[] arr = result.split(",");
            //发送成功
            if (Constant.STR_ZERO.equals(arr[0])) {
                smsLog.setSendId(arr[1]);
                smsLog.setInvalidNum(Integer.parseInt(arr[2]));
                smsLog.setSuccessNum(Integer.parseInt(arr[3]));
                smsLog.setBlackNum(Integer.parseInt(arr[4]));
                smsLog.setReturnMsg(arr[5]);
            } else {
                //发送失败
                smsLog.setReturnMsg(arr[1]);
            }
            smsLog.setSendStatus(Integer.parseInt(arr[0]));
            smsLog.setUserId(userId);
            smsLog.setSign(config.getSign());
            if (null == smsLog.getStime()) {
                smsLog.setStime(new Date());
            }
        }
        /**
         * 待补充
         */

        //保存发送记录
        save(smsLog);
        return smsLog;
    }

    @Override
    public SysSmsLogEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<SysSmsLogEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<SysSmsLogEntity> listPage(int current, int size, Map<String, Object> params) {
        Page<SysSmsLogEntity> page = new Query<SysSmsLogEntity>(current,size,params).getPage();
        List<SysSmsLogEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<SysSmsLogEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(SysSmsLogEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(SysSmsLogEntity entity) {
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
