package com.syg.base.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * REST API 返回结果
 * </p>
 *
 * @author geekidea
 * @since 2018-11-08
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Result<T> implements Serializable {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time  = new Date();

    /**
     * 无参构造方法
     */
    public Result() {

    }

    /**
     * 参数：是否成功
     * @param flag
     * @return
     */
    public static Result result(boolean flag){
        if (flag){
            return ok();
        }
        return fail();
    }

    /**
     * 参数：状态码，消息
     * @param CodeMsg
     * @return
     */
    public static Result result(CodeMsg CodeMsg){
        return result(CodeMsg,null);
    }

    /**
     * 参数：响应状态码，提示，数据
     * @param CodeMsg
     * @param data
     * @return
     */
    public static Result result(CodeMsg CodeMsg,Object data){
        return result(CodeMsg,null,data);
    }

    /**
     * 参数：响应状态码，提示，数据
     * @param codeMsg
     * @param msg
     * @param data
     * @return
     */
    public static Result result(CodeMsg codeMsg,String msg,Object data){
        boolean success = false;
        if (codeMsg.getCode() == CodeMsg.SUCCESS.getCode()){
            success = true;
        }
        String message = codeMsg.getMsg();
        if (StringUtils.isNotBlank(msg)){
            message = msg;
        }
        return Result.builder()
                .code(codeMsg.getCode())
                .msg(message)
                .data(data)
                .success(success)
                .time(new Date())
                .build();
    }

    /**
     * 参数：成功，不返回数据
     * @return
     */
    public static Result ok(){
        return ok(null);
    }

    /**
     * 参数：成功，返回数据
     * @param data
     * @return
     */
    public static Result ok(Object data){
        return result(CodeMsg.SUCCESS,data);
    }

    /**
     * 参数：成功，返回数据，消息提示
     * @param data
     * @param msg
     * @return
     */
    public static Result ok(Object data,String msg){
        return result(CodeMsg.SUCCESS,msg,data);
    }

    /**
     * 参数：成功，key, value数据，消息提示
     * @param key
     * @param value
     * @return
     */
    public static Result okMap(String key,Object value){
        Map<String,Object> map = new HashMap<>();
        map.put(key,value);
        return ok(map);
    }

    /**
     * 参数：失败枚举
     * @param CodeMsg
     * @return
     */
    public static Result fail(CodeMsg CodeMsg){
        return result(CodeMsg,null);
    }

    /**
     * 参数：失败提示消息
     * @param msg
     * @return
     */
    public static Result fail(String msg){
        return result(CodeMsg.FAIL,msg,null);

    }

    /**
     * 参数：失败提示消息
     * @param codeMsg
     * @param data
     * @return
     */
    public static Result fail(CodeMsg codeMsg,Object data){
        if (CodeMsg.SUCCESS == codeMsg){
            throw new RuntimeException("失败结果状态码不能为" + CodeMsg.SUCCESS.getCode());
        }
        return result(codeMsg,data);

    }

    /**
     * 参数：失败，key, value数据，消息提示
     * @param key
     * @param value
     * @return
     */
    public static Result failMap(String key,Object value){
        Map<String,Object> map = new HashMap<>();
        map.put(key,value);
        return result(CodeMsg.FAIL,map);
    }

    /**
     * 失败
     * @return
     */
    public static Result fail() {
        return fail(CodeMsg.FAIL);
    }
}