package com.syg.exception;


import com.syg.common.response.Result;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -778142600038732285L;

    private Integer code;
    private String message;


    public BusinessException(String message) {
        this(Result.CODE_ERROR_BUSINESS, message);
    }

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;

    }

    public static BusinessException authException() {
        return new BusinessException(401, "访问被拒绝");
    }

    public static BusinessException tokenException() {
        return new BusinessException(403, "token失效或已过期");
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public Result getResponse() {
        return Result.errorMsg(code, message);
    }
}
