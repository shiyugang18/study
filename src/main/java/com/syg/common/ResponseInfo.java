package com.syg.common;

import java.io.Serializable;

public class ResponseInfo<T> implements Serializable {
    public static final String CODE_SUCCESS = "0000";
    public static final String CODE_ERROR = "9999";
    public static final String CODE_ERROR_BUSINESS = "7777";

    public static final String CODE_SUCCESS_MSG = "成功";
    public static final String CODE_ERROR_MSG = "系统异常，请稍后再试";
    private static final long serialVersionUID = 0x20170713;
    private String code;

    private String msg;

    private T data;

    public ResponseInfo(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseInfo<T> success(T data) {
        return new ResponseInfo<>(CODE_SUCCESS, CODE_SUCCESS_MSG, data);
    }

    public static <T> ResponseInfo<T> success(String msg, T data) {
        return new ResponseInfo<>(CODE_SUCCESS, msg, data);
    }

    public static <T> ResponseInfo<T> assertion(T data) {
        if (data != null) {
            return success(data);
        } else {
            return new ResponseInfo<>("9444", "数据不存在", data);
        }
    }

    public static <T> ResponseInfo<T> error(T data) {
        return new ResponseInfo<>(CODE_ERROR, CODE_ERROR_MSG, data);
    }


    public static <T> ResponseInfo<T> error(String code, String msg, T data) {
        return new ResponseInfo<>(code, msg, data);
    }

    public boolean isSuccess() {
        return CODE_SUCCESS.equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
