package com.syg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: shiyugang
 * @Date: 2019/11/17 21:25
 */
@Component
@PropertySource(value = {"classpath:application.yml"}, encoding = "utf-8")
public class Config {

    @Value("${resources.midpHost}")
    private String midpHost;

    public String getMidpHost() {
        return midpHost;
    }

    @Value("${jwt.SECRET}")
    private String screte;

    public String getScrete() {
        return screte;
    }

    @Value("${jwt.EXPIRATIONTIME}")
    private String expirationtime;

    public String getExpirationtime() {
        return expirationtime;
    }
}
