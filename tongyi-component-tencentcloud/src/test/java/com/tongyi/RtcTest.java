package com.tongyi;

import com.tongyi.common.exception.TongyiException;
import com.tongyi.tencentcloud.rtc.service.ICallBackService;
import com.tongyi.tencentcloud.rtc.service.ICallQualityService;
import com.tongyi.tencentcloud.rtc.service.IRoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 程序发送模板消息demo
 */
@SpringBootTest
class RtcTest {
    @Autowired
    ICallBackService callBackService;

    @Autowired
    ICallQualityService callQualityService;
    @Autowired
    private IRoomService roomService;

    /**
     * 发送模板消息给用户
     * 添加消息模板指引：https://kf.qq.com/faq/170209E3InyI170209nIF7RJ.html
     * 示例消息模板为：{{first.DATA}} ↵商品名称：{{keyword1.DATA}} ↵购买时间：{{keyword2.DATA}} ↵{{remark.DATA}}
     */
    @Test
    void checkSign() {
        callBackService.checkSign(0L,"");
    }
    @Test
    public void listRoom() throws TongyiException, ParseException {
        Date sdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-12-10 00:00:00");
        Date edate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-12-11 00:00:00");
        ICallQualityService.RoomState[] list = callQualityService.describeRoomInformation(null,sdate,edate,0,10);
        System.out.println(list.length);
        for(ICallQualityService.RoomState item:list) {
            System.out.println(item);
        }
    }
    @Test
    public void dismissRoom() throws TongyiException {
        roomService.dismissRoom(42102376);
    }
    @Test
    public void roomDetail() throws ParseException, TongyiException {
        Date sdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-12-10 00:00:00");
        Date edate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-12-11 00:00:00");
        ICallQualityService.Event[] list = callQualityService.describeDetailEvent("42102376","1400607513_42102376_1639129356","666",sdate,edate);
        System.out.println(list.length);
        for(ICallQualityService.Event item:list) {
            System.out.println(item);
        }

    }
}
