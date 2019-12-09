package com.syg.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: Result
 * @Description: //TODO 用于控制器类返回结果
 * @Author: shiyugang
 * @Date: 2019/4/25 21:03
 * @Version: V1.0
 */
@Data //getter、setter方法
@NoArgsConstructor //无参构造方法
@Accessors(chain = true) //链式结构
public class Result implements Serializable {


    public static final Integer CODE_SUCCESS = 0000;
    public static final Integer CODE_ERROR = 9999;
    public static final Integer CODE_ERROR_BUSINESS = 7777;

    public static final String CODE_SUCCESS_MSG = "成功";
    public static final String CODE_ERROR_MSG = "系统异常，请稍后再试";

    private Boolean  success;  //是否成功   

    private Integer code;  // 返回码   

    private String msg;//返回信息  

    private Object data;   // 返回数据

    public Result(Boolean flag, Integer code, String msg, Object data) {
        this.success = flag;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 响应成功(带返回数据)
     * @param data 返回数据
     * @return Result
     */
    public static Result success(Object data){
        return new Result(true,2000,"响应成功",data);
    }

    /**
     * 响应成功
     * @return Result
     */
    public static Result success(){
        return new Result(true,2000,"响应成功",null);
    }

    /**
     * 响应错误(不带状态码,带消息)
     * @return Result
     */
    public static Result error(String msg){
        return new Result(false,2400,msg,null);
    }

    /**
     * 响应错误(带错误码,消息提醒)
     * @return
     */
    public static Result errorMsg(Integer code,String msg){
        return new Result(false,code,msg,null);
    }

}
