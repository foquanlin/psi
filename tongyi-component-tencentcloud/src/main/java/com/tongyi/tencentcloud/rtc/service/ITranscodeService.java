package com.tongyi.tencentcloud.rtc.service;

import com.tencentcloudapi.trtc.v20190722.models.EncodeParams;
import com.tencentcloudapi.trtc.v20190722.models.LayoutParams;
import com.tencentcloudapi.trtc.v20190722.models.OutputParams;
import com.tencentcloudapi.trtc.v20190722.models.PublishCdnParams;
import com.tongyi.common.exception.TongyiException;

/**
 * 惠州市酷天科技有限公司
 * 混流转码接口
 * @author foquanlin@163.com 林佛权
 * 2021-10-17
 */
public interface ITranscodeService {
    /**
     * 启动云端混流
     * Action 	是 	String 	公共参数，本接口取值：StartMCUMixTranscode。
     * Version 	是 	String 	公共参数，本接口取值：2019-07-22。
     * Region 	是 	String 	公共参数，详见产品支持的 地域列表。
     * SdkAppId 	是 	Integer 	TRTC的SDKAppId。
     * RoomId 	是 	Integer 	房间号。
     * OutputParams 	是 	OutputParams 	混流输出控制参数。
     * EncodeParams 	是 	EncodeParams 	混流输出编码参数。
     * LayoutParams 	是 	LayoutParams 	混流输出布局参数。
     * PublishCdnParams 	否 	PublishCdnParams 	第三方CDN转推参数。
     */
    String startMCUMixTranscode(Integer roomId, OutputParams outputParams, EncodeParams encodeParams, LayoutParams layoutParams, PublishCdnParams publishCdnParams) throws TongyiException;

    String stopMCUMixTranscode(Integer roomId) throws TongyiException;

    String startMCUMixTranscodeByStrRoomId(String roomId,OutputParams outputParams, EncodeParams encodeParams, LayoutParams layoutParams, PublishCdnParams publishCdnParams) throws TongyiException;
    String stopMCUMixTranscodeByStrRoomId(String roomId) throws TongyiException;
}
