package com.syg.domain.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: shiyugang
 * @Date: 2019/12/8 14:33
 */
@Data
public class SystemPermission {

    private String id;

    private String name;

    private String permission;

    private String url;

    private Integer isEnable;

    private String createBy;

    private Date createTime;

    private Date updateBy;

    private Date updateTime;

}
