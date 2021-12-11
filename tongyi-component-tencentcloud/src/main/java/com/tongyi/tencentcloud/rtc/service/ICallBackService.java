package com.tongyi.tencentcloud.rtc.service;

/**
 * 惠州市酷天科技有限公司
 *  回调事件处理
 * @author foquanlin@163.com 林佛权
 * 2021-12-11
 */
public interface ICallBackService {
    public void checkSign(Long appid,String sign);

    public void registerListener(String event,Listener listener);
    public void removeListener(String event);
    public String process(String json);
    public interface Listener{
        public void onEvent(String key,Object obj);
    };
}
