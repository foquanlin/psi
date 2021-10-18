package com.tongyi.alipay.service;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.facetoface.Client;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePayResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.tongyi.alipay.config.AliPayProperties;
import com.tongyi.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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
        AlipayTradePrecreateResponse atpr = null;
        try {
            Client client = Factory.Payment.FaceToFace();
            atpr = client.preCreate(subject,outTradeNo,amount.toPlainString());
        } catch (Exception e) {
            log.error("支付宝接口异常:",e);
            throw new BusinessException("支付宝接口异常:",e.getCause());
        }
        if (null == atpr){
            throw new BusinessException("支付宝没有返回");
        }
        if (!"10000".equalsIgnoreCase(atpr.code)){
            throw new BusinessException(String.format("[%s]%s",atpr.subCode,atpr.subMsg));
        }

        AlipayTradePayResponse rsp = null;
        try {
            Client client = Factory.Payment.FaceToFace();
            rsp = client.pay(subject,outTradeNo,amount.toPlainString(),authCode);
        } catch (Exception e) {
            log.error("支付宝接口异常:",e);
            throw new BusinessException("支付宝接口异常:",e.getCause());
        }
        if (null == rsp){
            throw new BusinessException("支付宝没有返回");
        }
        if (!"10000".equalsIgnoreCase(rsp.code)){
            throw new BusinessException(String.format("[%s]%s",rsp.subCode,rsp.subMsg));
        }
    }
}
