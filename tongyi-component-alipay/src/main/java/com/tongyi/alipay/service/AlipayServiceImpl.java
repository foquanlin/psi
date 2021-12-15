package com.tongyi.alipay.service;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.facetoface.Client;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePayResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.tongyi.alipay.config.AliPayProperties;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-10-17
 */
@Slf4j
public class AlipayServiceImpl implements IAlipayService {

    public AlipayServiceImpl() {
    }

    public AlipayServiceImpl(AliPayProperties config) {
        Factory.setOptions(config.newConfig());
    }

    @Override
    public void faceToFace(String subject, String outTradeNo, BigDecimal amount,String authCode) {
        if (StringUtils.isNullOrEmpty(outTradeNo)){
            throw new BusinessException("请填写订单号");
        }
        if (StringUtils.isNullOrEmpty(amount)){
            throw new BusinessException("请填写金额");
        }
        if (StringUtils.isNullOrEmpty(authCode)){
            throw new BusinessException("没有付款码");
        }
        AlipayTradePrecreateResponse atpr = null;
        try {
            Client client = Factory.Payment.FaceToFace();
            atpr = client.preCreate(subject,outTradeNo,amount.setScale(2, RoundingMode.HALF_UP).toPlainString());
            log.info("创建支付宝付款单:{}",atpr);
        } catch (Exception e) {
            log.error("支付宝接口异常:",e);
            throw new BusinessException("支付宝接口异常:",e.getCause());
        }
        if (null == atpr){
            throw new BusinessException("支付宝没有返回");
        }
        if (!"10000".equalsIgnoreCase(atpr.code)){
            log.error("创建付款异常:code={},msg={}",atpr.subCode,atpr.subMsg);
            throw new BusinessException(String.format("[%s]%s",atpr.subCode,atpr.subMsg));
        }

        AlipayTradePayResponse rsp = null;
        try {
            log.info("支付宝开始扣款:说明={},金额={},付款码={}",subject,amount.setScale(2, RoundingMode.HALF_UP).toPlainString(),authCode);
            Client client = Factory.Payment.FaceToFace();
            rsp = client.pay(subject,outTradeNo,amount.setScale(2, RoundingMode.HALF_UP).toPlainString(),authCode);
            log.info("面对面支付完成:{}",rsp);
        } catch (Exception e) {
            log.error("支付宝接口异常:",e);
            throw new BusinessException("支付宝接口异常:",e.getCause());
        }
        if (null == rsp){
            throw new BusinessException("支付宝没有返回");
        }
        if (!"10000".equalsIgnoreCase(rsp.code)){
            log.error("扣款异常:code={},msg={}",rsp.subCode,rsp.subMsg);
            throw new BusinessException(String.format("[%s]%s",rsp.subCode,rsp.subMsg));
        }
    }
}
