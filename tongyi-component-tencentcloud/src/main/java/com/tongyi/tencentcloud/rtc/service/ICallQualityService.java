package com.tongyi.tencentcloud.rtc.service;

import com.tongyi.common.exception.TongyiException;
import lombok.Data;

import java.util.Date;

/**
 * 惠州市酷天科技有限公司
 * 通话质量监控相关接口
 * @author foquanlin@163.com 林佛权
 * 2021-12-11
 */
public interface ICallQualityService {
    /**
     * 查询历史房间列表
     * @param roomId
     * @param sdate
     * @param edate
     * @param page
     * @param size
     * @return
     */
    public RoomState[] describeRoomInformation(String roomId, Date sdate, Date edate, int page, int size) throws TongyiException;

    /**
     * 查询详细事件
     * @param roomId
     * @param commId
     * @param userId
     * @param sdate
     * @param edate
     * @return
     */
    public Event[] describeDetailEvent(String roomId,String commId,String userId,Date sdate,Date edate) throws TongyiException;


    @Data
    public class RoomState {
        private String CommId;
        private String RoomString;
        private Long CreateTime;
        private Long DestroyTime;
        private Boolean IsFinished;
        private String UserId;
    }

    @Data
    public class Event{
        private String peerId;
        private EventMessage[] content;
    }
    @Data
    public class EventMessage{
        private Long type;
        private Long time;
        private Long eventId;
        private Long paramOne;
        private Long paramTwo;
    }
}
