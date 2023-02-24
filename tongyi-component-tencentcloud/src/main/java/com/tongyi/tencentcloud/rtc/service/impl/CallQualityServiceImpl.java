package com.tongyi.tencentcloud.rtc.service.impl;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.trtc.v20190722.TrtcClient;
import com.tencentcloudapi.trtc.v20190722.models.*;
import com.tongyi.common.exception.TongyiException;
import com.tongyi.tencentcloud.rtc.service.ICallQualityService;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-12-11
 */
@Slf4j
public class CallQualityServiceImpl implements ICallQualityService {
    private TrtcClient client;
    private Long appid;

    public CallQualityServiceImpl(TrtcClient client,Long appid){
        this.client = client;
        this.appid = appid;
    }

    @Override
    public RoomState[] describeRoomInformation(String roomId, Date sdate, Date edate, int page, int size) throws TongyiException {
        DescribeRoomInformationRequest req = new DescribeRoomInformationRequest();
        req.setSdkAppId(String.valueOf(appid));
        req.setRoomId(roomId);
        req.setStartTime(sdate.getTime()/1000);
        req.setEndTime(edate.getTime()/1000);
        req.setPageNumber(String.valueOf(page));
        req.setPageSize(String.valueOf(size));
        try {
            DescribeRoomInformationResponse rsp = client.DescribeRoomInformation(req);
            com.tencentcloudapi.trtc.v20190722.models.RoomState[] list = rsp.getRoomList();
            RoomState[] items = new RoomState[list.length];
            for(int i=0;i< list.length;i++){
                items[i] = newItem(list[i]);
            }
            return items;
        } catch (TencentCloudSDKException e) {
            log.error("查询历史房间列表,异常{}:",roomId,e);
            TongyiException.throwException(500,"查询历史房间列表",e);
        }
        return null;
    }

    @Override
    public Event[] describeDetailEvent(String roomId, String commId, String userId, Date sdate, Date edate) throws TongyiException {
        DescribeDetailEventRequest req = new DescribeDetailEventRequest();
        req.setCommId(commId);
        req.setRoomId(roomId);
        req.setUserId(userId);
        req.setStartTime(sdate.getTime()/1000);
        req.setEndTime(edate.getTime()/1000);
        try {
            DescribeDetailEventResponse rsp = client.DescribeDetailEvent(req);
            com.tencentcloudapi.trtc.v20190722.models.EventList[] list = rsp.getData();
            Event[] items = new Event[list.length];
            for(int i=0;i< list.length;i++){
                items[i] = newEventItem(list[i]);
            }
            return items;
        } catch (TencentCloudSDKException e) {
            log.error("查询详细事件,异常{}:",roomId,e);
            TongyiException.throwException(500,"查询详细事件",e);
        }
        return null;
    }

    private Event newEventItem(EventList item) {
        Event event = new Event();
        event.setPeerId(item.getPeerId());
        com.tencentcloudapi.trtc.v20190722.models.EventMessage[] lists = item.getContent();
        EventMessage[] items = new EventMessage[lists.length];
        for(int i=0;i< lists.length;i++) {
            items[i] = newMessageItem(lists[0]);
        }
        event.setContent(items);
        return event;
    }

    private EventMessage newMessageItem(com.tencentcloudapi.trtc.v20190722.models.EventMessage item) {
        EventMessage msg = new EventMessage();
        msg.setEventId(item.getEventId());
        msg.setParamOne(item.getParamOne());
        msg.setType(item.getType());
        msg.setParamTwo(item.getParamTwo());
        msg.setTime(item.getTime());
        return msg;
    }

    private RoomState newItem(com.tencentcloudapi.trtc.v20190722.models.RoomState roomState) {
        RoomState state = new RoomState();
        state.setCommId(roomState.getCommId());
        state.setCreateTime(roomState.getCreateTime());
        state.setDestroyTime(roomState.getDestroyTime());
        state.setIsFinished(roomState.getIsFinished());
        state.setRoomString(roomState.getRoomString());
        state.setUserId(roomState.getUserId());
        return state;
    }
}
