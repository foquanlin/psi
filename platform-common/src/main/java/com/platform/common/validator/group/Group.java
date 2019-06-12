/*
 * 项目名称:platform-plus
 * 类名称:Group.java
 * 包名称:com.platform.common.validator.group
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * @author 李鹏军
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
