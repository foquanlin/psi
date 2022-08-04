package com.tongyi.common.exception;


/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-12-11
 */
public class TongyiException extends Exception  {
    private String msg;
    private int code = 500;

    public TongyiException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public TongyiException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public TongyiException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public TongyiException(String msg, int code, Throwable e) {
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

    public static void throwException(int code ,String msg) throws TongyiException {
        throw new TongyiException(msg,code);
    }
    public static void throwException(String msg) throws TongyiException {
        throw new TongyiException(msg);
    }
    public static void throwException(String msg,Throwable e) throws TongyiException {
        throw new TongyiException(msg,e);
    }
    public static void throwException(int code, String msg, Throwable e) throws TongyiException {
        throw new TongyiException(msg,code,e);
    }
}
