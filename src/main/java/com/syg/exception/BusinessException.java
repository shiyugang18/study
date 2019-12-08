package com.syg.exception;


import com.syg.base.response.Response;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -778142600038732285L;
    private String message;
    private String code;

    public BusinessException(String message) {
        this(Response.CODE_ERROR_BUSINESS, message);
    }

    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;

    }

    public static BusinessException authException() {
        return new BusinessException("401", "访问被拒绝");
    }

    public static BusinessException tokenException() {
        return new BusinessException("403", "token失效或已过期");
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public Response<Object> getResponse() {
        return Response.error(code, message, null);
    }
}
