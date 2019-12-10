package com.syg.base.enums;


/**
 * @Author: shiyugang
 * @Date: 2019/12/10 23:12
 */
public enum GradeEnum {

    /**
     * 小学,中学,高中
     */
    PRIMARY(1, "小学"),
    SECONDORY(2, "中学"),
    HIGH(3, "高中");

    GradeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    private int code;
    private String value;
}
