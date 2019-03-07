/*
 * 项目名称:platform-plus
 * 类名称:Token.java
 * 包名称:com.platform.modules.job.task
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/21 17:06    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.job.task;

import com.platform.modules.app.utils.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 李鹏军
 * tokenTask为spring bean的名称
 */
@Slf4j
@Component("tokenTask")
public class TokenTask {
    @Autowired
    WechatUtil wechatUtil;

    public void refreshToken() {
        wechatUtil.refreshToken();
    }
}
