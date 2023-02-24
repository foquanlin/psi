package com.tongyi.core;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-08-22
 */
public class ServiceException extends Exception {

    private int code = 500;
    private String msg;

    public ServiceException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ServiceException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ServiceException(int code,String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ServiceException(int code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static void throwException(String msg) throws ServiceException {
        throw new ServiceException(msg);
    }
    public static void throwException(String msg,Throwable e) throws ServiceException {
        throw new ServiceException(msg,e);
    }
    public static void throwException(int code,String msg) throws ServiceException {
        throw new ServiceException(code,msg);
    }
    public static void throwException(int code, String msg, Throwable e) throws ServiceException {
        throw new ServiceException(code,msg,e);
    }
}
