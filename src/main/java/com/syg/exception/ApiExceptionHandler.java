package com.syg.exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.syg.common.response.Result;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
@ResponseBody
public class ApiExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception exp) {
        log.info("异常：{}", exp.toString());
        if (exp.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ex = (InvalidFormatException) exp.getCause();
            InvalidResult invalidArgument = new InvalidResult();
            invalidArgument.setField(ex.getPath().toString());
            invalidArgument.setValue(ex.getValue());
            return new Result(false,9995, "参数不正确", invalidArgument);
        } else if (exp instanceof BusinessException) {
            BusinessException ex = (BusinessException) exp;
            return ex.getResponse();
        } else if (exp.getCause() instanceof BusinessException) {
            BusinessException ex = (BusinessException) exp.getCause();
            return ex.getResponse();
        } else if (exp.getCause() instanceof SQLIntegrityConstraintViolationException) {
            return new Result(false,9991, "数据重复或异常", "数据重复或异常");
        } else {
            log.error("未知异常", exp);
            return Result.error(null);
        }
    }


    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public Result argumentMismatchHandler(MethodArgumentTypeMismatchException exp) {
        return new Result(false,9996, "参数类型不匹配", exp.getName());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result argumentMissHandler(MissingServletRequestParameterException exp) {
        return new Result(false,9997, "参数不能为空", exp.getParameterName());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result argumentInvalidHandler(MethodArgumentNotValidException exp) {
        List<InvalidResult> invalidArguments = new ArrayList<>();
        for (FieldError error : exp.getBindingResult().getFieldErrors()) {
            InvalidResult invalidArgument = new InvalidResult();
            invalidArgument.setMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setValue(error.getRejectedValue());
            invalidArguments.add(invalidArgument);
        }
        return new Result(false,9998, "参数格式不正确", invalidArguments);
    }

    /*=========== Shiro 异常拦截==============*/



    private class InvalidResult {
        private String field;

        private Object value;

        private String message;

        public InvalidResult() {
        }

        public InvalidResult(String field, Object value, String message) {
            this.field = field;
            this.value = value;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
