package com.tongyi.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 飞鹅打印机工具类
 *
 * @author foquanlin
 */
public class PrintUtils {
    /**
     * 不需要修改
     */
    public static final String URL = "http://api.feieyun.cn/Api/Open/";
    /**
     * fixme 注册地址：http://admin.feieyun.com/regist.php
     * 必填：账号名
     */
    public String user = "147657060@qq.com";
    /**
     * 必填: 注册账号后生成的UKEY
     */
    public String ukey = "";
    private RequestConfig requestConfig;
    public PrintUtils(String user,String ukey){
        this.user = user;
        this.ukey = ukey;
        //通过POST请求，发送打印信息到服务器
        this.requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
    }
    /**
     * 测试时，打开下面注释掉方法的即可,更多接口文档信息,请访问官网开放平台查看
     *
     * @param args args
     * @throws Exception Exception
     */
    public static void main(String[] args) throws Exception {

        //==================添加打印机接口（支持批量）==================
        //***返回值JSON字符串***
        //正确例子：{"msg":"ok","ret":0,"data":{"ok":["sn#key#remark#carnum","316500011#abcdefgh#快餐前台"],"no":["316500012#abcdefgh#快餐前台#13688889999  （错误：识别码不正确）"]},"serverExecutedTime":3}
        //错误：{"msg":"参数错误 : 该帐号未注册.","ret":-2,"data":null,"serverExecutedTime":37}

        //提示：打印机编号(必填) # 打印机识别码(必填) # 备注名称(选填) # 流量卡号码(选填)，多台打印机请换行（\n）添加新打印机信息，每次最多100行(台)。
//			String snlist = "sn1#key1#remark1#carnum1\nsn2#key2#remark2#carnum2";
//			String method = addprinter(snlist);
//			System.out.println(method);


        //==================方法1.打印订单==================
        //***返回值JSON字符串***
        //成功：{"msg":"ok","ret":0,"data":"xxxxxxx_xxxxxxxx_xxxxxxxx","serverExecutedTime":5}
        //失败：{"msg":"错误描述","ret":非0,"data":"null","serverExecutedTime":5}

//			String method1 = printMsg(SN);
//			System.out.println(method1);


        //===========方法2.查询某订单是否打印成功=============
        //***返回值JSON字符串***
        //成功：{"msg":"ok","ret":0,"data":true,"serverExecutedTime":2}//data:true为已打印,false为未打印
        //失败：{"msg":"错误描述","ret":非0, "data":null,"serverExecutedTime":7}

//			String orderid = "xxxxxxx_xxxxxxxx_xxxxxxxx";//订单ID，从方法1返回值data获取
//			String method2 = queryOrderState(orderid);
//			System.out.println(method2);


        //===========方法3.查询指定打印机某天的订单详情============
        //***返回值JSON字符串***
        //成功：{"msg":"ok","ret":0,"data":{"print":6,"waiting":1},"serverExecutedTime":9}//print已打印，waiting为打印
        //失败：{"msg":"错误描述","ret":非0,"data":"null","serverExecutedTime":5}

//			String strdate = "2016-11-12";//注意时间格式为"yyyy-MM-dd"
//			String method3 = queryOrderInfoByDate(SN,strdate);
//			System.out.println(method3);


        //===========方法4.查询打印机的状态==========================
        //***返回值JSON字符串***
        //成功：{"msg":"ok","ret":0,"data":"状态","serverExecutedTime":4}
        //失败：{"msg":"错误描述","ret":非0,"data":"null","serverExecutedTime":5}

//			String method4 = queryPrinterStatus(SN);
//			System.out.println(method4);

    }


    /**
     * 发送用户需要打印的订单内容给飞鹅云打印机
     *
     * @param sn      打印机编号
     * @param content 打印内容,不能超过5000字节
     * @param times   打印次数，默认为1
     * @return 正确返回订单ID。
     */
    public String printMsg(String sn, String content, String times) {
//        标签说明：
//        单标签:
//        "<BR>"为换行,"<CUT>"为切刀指令(主动切纸,仅限切刀打印机使用才有效果)
//        "<LOGO>"为打印LOGO指令(前提是预先在机器内置LOGO图片),"<PLUGIN>"为钱箱或者外置音响指令
//        成对标签：
//        "<CB></CB>"为居中放大一倍,"<B></B>"为放大一倍,"<C></C>"为居中,<L></L>字体变高一倍
//        <W></W>字体变宽一倍,"<QR></QR>"为二维码,"<BOLD></BOLD>"为字体加粗,"<RIGHT></RIGHT>"为右对齐
//        拼凑订单内容时可参考如下格式
//        根据打印纸张的宽度，自行调整内容的格式，可参考下面的样例格式

//        String content;
//        content = "<CB>测试打印</CB><BR>";
//        content += "名称　　　　　 单价  数量 金额<BR>";
//        content += "--------------------------------<BR>";
//        content += "饭　　　　　　 1.0    1   1.0<BR>";
//        content += "炒饭　　　　　 10.0   10  10.0<BR>";
//        content += "蛋炒饭　　　　 10.0   10  100.0<BR>";
//        content += "鸡蛋炒饭　　　 100.0  1   100.0<BR>";
//        content += "番茄蛋炒饭　　 1000.0 1   100.0<BR>";
//        content += "西红柿蛋炒饭　 1000.0 1   100.0<BR>";
//        content += "西红柿鸡蛋炒饭 100.0  10  100.0<BR>";
//        content += "备注：加辣<BR>";
//        content += "--------------------------------<BR>";
//        content += "合计：xx.0元<BR>";
//        content += "送货地点：广州市南沙区xx路xx号<BR>";
//        content += "联系电话：13888888888888<BR>";
//        content += "订餐时间：2016-08-08 08:08:08<BR>";
//        content += "<QR>http://www.dzist.com</QR>";

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        HttpPost post = new HttpPost(URL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user", user));
        String STIME = String.valueOf(System.currentTimeMillis() / 1000);
        nvps.add(new BasicNameValuePair("stime", STIME));
        nvps.add(new BasicNameValuePair("sig", signature(user, ukey, STIME)));
        //固定值,不需要修改
        nvps.add(new BasicNameValuePair("apiname", "Open_printMsg"));
        nvps.add(new BasicNameValuePair("sn", sn));
        nvps.add(new BasicNameValuePair("content", content));
        //打印联数
        nvps.add(new BasicNameValuePair("times", times));

        return execute(post, nvps, httpClient);
    }

    /**
     * 根据订单ID,去查询订单是否打印成功
     *
     * @param orderid 订单ID，由printMsg犯法返回。
     * @return 已打印返回true, 未打印返回false。
     */
    public String queryOrderState(String orderid) {
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        HttpPost post = new HttpPost(URL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user", user));
        String STIME = String.valueOf(System.currentTimeMillis() / 1000);
        nvps.add(new BasicNameValuePair("stime", STIME));
        nvps.add(new BasicNameValuePair("sig", signature(user, ukey, STIME)));
        //固定值,不需要修改
        nvps.add(new BasicNameValuePair("apiname", "Open_queryOrderState"));
        nvps.add(new BasicNameValuePair("orderid", orderid));


        return execute(post, nvps, httpClient);
    }


    /**
     * 查询指定打印机某天的订单统计数
     *
     * @param sn      打印机编号
     * @param strdate 查询日期，格式YY-MM-DD，如：2016-09-20
     * @return 已打印订单数和等待打印数。
     */
    public String queryOrderInfoByDate(String sn, String strdate) {
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        HttpPost post = new HttpPost(URL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user", user));
        String STIME = String.valueOf(System.currentTimeMillis() / 1000);
        nvps.add(new BasicNameValuePair("stime", STIME));
        nvps.add(new BasicNameValuePair("sig", signature(user, ukey, STIME)));
        //固定值,不需要修改
        nvps.add(new BasicNameValuePair("apiname", "Open_queryOrderInfoByDate"));
        nvps.add(new BasicNameValuePair("sn", sn));
        //yyyy-MM-dd格式
        nvps.add(new BasicNameValuePair("date", strdate));


        return execute(post, nvps, httpClient);
    }


    /**
     * 查询指定打印机状态
     *
     * @param sn 打印机编号
     * @return 该打印机在线或离线，正常或异常的信息。
     */
    public String queryPrinterStatus(String sn) {
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        HttpPost post = new HttpPost(URL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user", user));
        String STIME = String.valueOf(System.currentTimeMillis() / 1000);
        nvps.add(new BasicNameValuePair("stime", STIME));
        nvps.add(new BasicNameValuePair("sig", signature(user, ukey, STIME)));
        //固定值,不需要修改
        nvps.add(new BasicNameValuePair("apiname", "Open_queryPrinterStatus"));
        nvps.add(new BasicNameValuePair("sn", sn));


        return execute(post, nvps, httpClient);
    }

    /**
     * @param post       post
     * @param nvps       nvps
     * @param httpClient httpClient
     * @return String
     */
    private String execute(HttpPost post, List<NameValuePair> nvps, CloseableHttpClient httpClient) {
        CloseableHttpResponse response = null;
        String result = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            response = httpClient.execute(post);
            int statecode = response.getStatusLine().getStatusCode();
            if (statecode == 200) {
                HttpEntity httpentity = response.getEntity();
                if (httpentity != null) {
                    //服务器返回的JSON字符串，建议要当做日志记录起来
                    result = EntityUtils.toString(httpentity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                post.abort();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 生成签名字符串
     *
     * @param USER
     * @param UKEY
     * @param STIME
     * @return
     */
    private String signature(String USER, String UKEY, String STIME) {
        return DigestUtils.sha1Hex(USER + UKEY + STIME);
    }
}
