package com.tongyi.tencentcloud.rtc.service.impl;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.trtc.v20190722.TrtcClient;
import com.tencentcloudapi.trtc.v20190722.models.*;
import com.tongyi.common.exception.TongyiException;
import com.tongyi.tencentcloud.rtc.service.ITranscodeService;
import lombok.extern.slf4j.Slf4j;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-12-11
 */
@Slf4j
public class TranscodeServiceImpl implements ITranscodeService {

    private TrtcClient client;
    private Long appid;

    public TranscodeServiceImpl(TrtcClient client,Long appid){
        this.client = client;
        this.appid = appid;
    }
    @Override
    public String startMCUMixTranscode(Integer roomId, OutputParams outputParams, EncodeParams encodeParams, LayoutParams layoutParams) throws TongyiException {
        StartMCUMixTranscodeRequest req = new StartMCUMixTranscodeRequest();
        req.setSdkAppId(appid);
        req.setRoomId(roomId.longValue());
        req.setOutputParams(outputParams);
        req.setEncodeParams(encodeParams);
        req.setLayoutParams(layoutParams);
        try {
            StartMCUMixTranscodeResponse rsp = client.StartMCUMixTranscode(req);
            return rsp.getRequestId();
        } catch (TencentCloudSDKException e) {
            log.error("启动云端混流异常:roomId={}",roomId,e);
            TongyiException.throwException(500,"启动云端混流异常",e);
        }
        return null;
    }

    @Override
    public String stopMCUMixTranscode(Integer roomId) throws TongyiException {
        StopMCUMixTranscodeRequest req = new StopMCUMixTranscodeRequest();
        req.setSdkAppId(appid);
        req.setRoomId(roomId.longValue());
        try {
            StopMCUMixTranscodeResponse rsp = client.StopMCUMixTranscode(req);
            return rsp.getRequestId();
        } catch (TencentCloudSDKException e) {
            log.error("停止云端混流异常:roomId={}",roomId,e);
            TongyiException.throwException(500,"启动云端混流异常",e);
        }
        return null;
    }

    @Override
    public String startMCUMixTranscodeByStrRoomId(String roomId, OutputParams outputParams, EncodeParams encodeParams, LayoutParams layoutParams) throws TongyiException {
        StartMCUMixTranscodeByStrRoomIdRequest req = new StartMCUMixTranscodeByStrRoomIdRequest();
        req.setSdkAppId(appid);
        req.setStrRoomId(roomId);
        req.setOutputParams(outputParams);
        req.setEncodeParams(encodeParams);
        req.setLayoutParams(layoutParams);
        try {
            StartMCUMixTranscodeByStrRoomIdResponse rsp = client.StartMCUMixTranscodeByStrRoomId(req);
            return rsp.getRequestId();
        } catch (TencentCloudSDKException e) {
            log.error("启动云端混流异常:roomId={}",roomId,e);
            TongyiException.throwException(500,"启动云端混流异常",e);
        }
        return null;
    }

    @Override
    public String stopMCUMixTranscodeByStrRoomId(String roomId) throws TongyiException {
        StopMCUMixTranscodeByStrRoomIdRequest req = new StopMCUMixTranscodeByStrRoomIdRequest();
        req.setSdkAppId(appid);
        req.setStrRoomId(roomId);
        try {
            StopMCUMixTranscodeByStrRoomIdResponse rsp = client.StopMCUMixTranscodeByStrRoomId(req);
            return rsp.getRequestId();
        } catch (TencentCloudSDKException e) {
            log.error("停止云端混流异常:roomId={}",roomId,e);
            TongyiException.throwException(500,"停止云端混流异常",e);
        }
        return null;
    }
}
