package com.tongyi.tencentcloud.rtc.service.impl;

import com.tencentcloudapi.trtc.v20190722.TrtcClient;
import com.tongyi.tencentcloud.rtc.service.ICallBackService;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-12-11
 */
@Slf4j
public class CallBackServiceImpl implements ICallBackService {
    private TrtcClient client;
    private Long appid;
    private String callbackKey;
    private Map<String,Listener> listeners = new HashMap<>();

    public CallBackServiceImpl(TrtcClient client,Long appid,String callbackKey){
        this.client = client;
        this.appid = appid;
        this.callbackKey = callbackKey;
    }

    @Override
    public void checkSign(Long appid, String sign) {

    }

    @Override
    public void registerListener(String event, Listener listener) {
        Map<String,Listener> map = new HashMap<>();
        map.putAll(listeners);
        map.put(event,listener);
        this.listeners = map;
    }

    @Override
    public void removeListener(String event) {
        Map<String,Listener> map = new HashMap<>();
        map.putAll(listeners);
        map.remove(event);
        this.listeners = map;
    }

    @Override
    public String process(String json) {
        for(Map.Entry<String,Listener> item:listeners.entrySet()){
            item.getValue().onEvent(item.getKey(),json);
        }
        return null;
    }
}
