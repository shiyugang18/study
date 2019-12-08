package com.syg.domain.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: shiyugang
 * @Date: 2019/12/8 14:33
 */
@Data
public class SystemUserRole {

    private String id;

    private String userId;

    private String roleId;

    private Integer isEnable;

    private String createBy;

    private Date createTime;

    private Date updateBy;

    private Date updateTime;

}
