package com.my.liufeng.chat.exception;

/**
 * rpc框架内部异常
 */
public class InnerException extends OuterException {

    public InnerException(String msg) {
        super(msg);
    }

}
