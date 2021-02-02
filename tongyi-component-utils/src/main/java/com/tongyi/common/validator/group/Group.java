/*
 * 项目名称:tongyi-component
 * 类名称:Group.java
 * 包名称:com.tongyi.common.validator.group
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * @author 林佛权
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
