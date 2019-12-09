package com.syg.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.Assert;

/**
 * RedisKey 枚举类
 */
@Getter
public enum RedisKeyEnum {

    /**
     * key较多的情况：不定义具体的key, key当参数传给keyBuilder
     */
    //验证码
    APPLY_LOGIN_CODE("APPLY", "LOGIN", "CODE"),
    //登录缓存
    APPLY_LOGIN_DATA("APPLY", "LOGIN", "DATA");


    /**
     * 系统标识
     */
    private String keyPrefix;
    /**
     * 模块名称
     */
    private String module;
    /**
     * 方法名称
     */
    private String func;
    /**
     * key
     */
    private String key;


    RedisKeyEnum(String keyPrefix, String module, String func) {
        this.keyPrefix = keyPrefix;
        this.module = module;
        this.func = func;
    }

    RedisKeyEnum(String keyPrefix, String module, String func, String key) {
        this.keyPrefix = keyPrefix;
        this.module = module;
        this.func = func;
        this.key = key;
    }

    public String keyBuilder() {
        return checkAndGetValue(key);
    }

    public String keyBuilder(String key) {
        return checkAndGetValue(key);
    }

    private String checkAndGetValue(String key) {
        Assert.notNull(keyPrefix, "RedisKeyEnum: keyPrefix can not be null");
        Assert.notNull(module, "RedisKeyEnum: module can not be null");
        Assert.notNull(func, "RedisKeyEnum: func can not be null");
        Assert.notNull(key, "RedisKeyEnum: key can not be null");
        return keyPrefix + ":" + module + ":" + func + ":" + key;
    }

    /***
     * Redis常量key在此枚举中申明
     *
     * @author hulilei
     * @date 2019/6/11
     */
    @Getter
    @AllArgsConstructor
    enum ConstantKeyEnum {
        /**
         * 常量Key样例
         */
        LMDM_TOKEN_EXPIRE_TIME("LMDM:TOKEN:EXPIRE:TIME", "token过期时间"),
        LMDM_SMS_CODE_EXPIRE("LMDM:SMS:CODE:EXPIRE", "短信验证码过期时间");
        String key;
        String remark;
    }

}

