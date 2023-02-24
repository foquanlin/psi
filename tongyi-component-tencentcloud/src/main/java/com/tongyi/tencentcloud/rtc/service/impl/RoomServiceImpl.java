package com.tongyi.tencentcloud.rtc.service.impl;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.trtc.v20190722.TrtcClient;
import com.tencentcloudapi.trtc.v20190722.models.*;
import com.tongyi.common.exception.TongyiException;
import com.tongyi.tencentcloud.rtc.service.IRoomService;
import lombok.extern.slf4j.Slf4j;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-12-11
 */

@Slf4j
public class RoomServiceImpl implements IRoomService {

    private TrtcClient client;
    private Long appid;

    public RoomServiceImpl(TrtcClient client,Long appid){
        this.client = client;
        this.appid = appid;
    }
    @Override
    public String removeUser(Integer roomId, String id) throws TongyiException {
        RemoveUserResponse rsp = null;
        try {
            RemoveUserRequest req = new RemoveUserRequest();
            req.setSdkAppId(appid);
            req.setRoomId(roomId.longValue());
            req.setUserIds(new String[]{id});
            rsp = client.RemoveUser(req);
            return rsp.getRequestId();
        } catch (TencentCloudSDKException e) {
            log.error("移出用户异常:roomId={},id={}",roomId,id,e);
            TongyiException.throwException(500,"移出用户异常",e);
        }
        return null;
    }

    @Override
    public String removeUser(Integer roomId, String[] ids) throws TongyiException {
        RemoveUserResponse rsp = null;
        try {
            RemoveUserRequest req = new RemoveUserRequest();
            req.setSdkAppId(appid);
            req.setRoomId(roomId.longValue());
            req.setUserIds(ids);
            rsp = client.RemoveUser(req);
            return rsp.getRequestId();
        } catch (TencentCloudSDKException e) {
            log.error("移出用户异常:roomId={},id={}",roomId,ids,e);
            TongyiException.throwException(500,"移出用户异常",e);
        }
        return null;
    }

    @Override
    public String dismissRoom(Integer roomId) throws TongyiException {
        DismissRoomResponse rsp = null;
        try {
            DismissRoomRequest req = new DismissRoomRequest();
            req.setSdkAppId(appid);
            req.setRoomId(roomId.longValue());
            rsp = client.DismissRoom(req);
            return rsp.getRequestId();
        } catch (TencentCloudSDKException e) {
            log.error("移出用户异常:roomId={}",roomId,e);
            TongyiException.throwException(500,"移出用户异常",e);
        }
        return null;
    }

    @Override
    public String removeUserByStrRoomId(String roomId, String id) throws TongyiException {
        RemoveUserByStrRoomIdResponse rsp = null;
        try {
            RemoveUserByStrRoomIdRequest req = new RemoveUserByStrRoomIdRequest();
            req.setSdkAppId(appid);
            req.setRoomId(roomId);
            req.setUserIds(new String[]{id});
            rsp = client.RemoveUserByStrRoomId(req);
            return rsp.getRequestId();
        } catch (TencentCloudSDKException e) {
            log.error("移出用户异常:roomId={},id={}",roomId,id,e);
            TongyiException.throwException(500,"移出用户异常",e);
        }
        return null;
    }

    @Override
    public String removeUserByStrRoomId(String roomId, String[] ids) throws TongyiException {
        RemoveUserByStrRoomIdResponse rsp = null;
        try {
            RemoveUserByStrRoomIdRequest req = new RemoveUserByStrRoomIdRequest();
            req.setSdkAppId(appid);
            req.setRoomId(roomId);
            req.setUserIds(ids);
            rsp = client.RemoveUserByStrRoomId(req);
            return rsp.getRequestId();
        } catch (TencentCloudSDKException e) {
            log.error("移出用户异常:roomId={},id={}",roomId,ids,e);
            TongyiException.throwException(500,"移出用户异常",e);
        }
        return null;
    }

    @Override
    public String dismissRoomByStrRoomId(String roomId) throws TongyiException {
        DismissRoomByStrRoomIdResponse rsp = null;
        try {
            DismissRoomByStrRoomIdRequest req = new DismissRoomByStrRoomIdRequest();
            req.setSdkAppId(appid);
            req.setRoomId(roomId);
            rsp = client.DismissRoomByStrRoomId(req);
            return rsp.getRequestId();
        } catch (TencentCloudSDKException e) {
            log.error("移出用户异常:roomId={}",roomId,e);
            TongyiException.throwException(500,"移出用户异常",e);
        }
        return null;
    }
}
