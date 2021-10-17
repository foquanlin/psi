package com.tongyi.alipay.service;

import java.math.BigDecimal;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-10-17
 */
public interface IAlipayService {
    void faceToFace(String subject, String outTradeNo, BigDecimal amount,String authCode);
}
