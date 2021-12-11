package com.tongyi.tencentcloud.rtc.service;

import com.tongyi.common.exception.TongyiException;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 惠州市酷天科技有限公司
 * 房间管理相关接口
 * @author foquanlin@163.com 林佛权
 * 2021-12-11
 */
public interface IRoomService {
    /**
     * 移出用户接口
     * @return
     */
    public String removeUser(Integer roomId, String id) throws TongyiException;
    public String removeUser(Integer roomId, String[] ids) throws TongyiException;

    /**
     * 解散房间
     */
    public String dismissRoom(Integer roomId) throws TongyiException;
    /**
     * 移出用户接口
     */
    public String removeUserByStrRoomId(String roomId, String id) throws TongyiException;
    public String removeUserByStrRoomId(String roomId, String[] ids) throws TongyiException;
    /**
     * 解散房间
     */
    public String dismissRoomByStrRoomId(String roomId) throws TongyiException;
}
