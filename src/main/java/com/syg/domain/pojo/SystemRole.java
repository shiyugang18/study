package com.syg.domain.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: shiyugang
 * @Date: 2019/12/8 14:33
 */
@Data
public class SystemRole {

    private String id;

    private String name;

    private String role;

    private Integer isEnable;

    private String createBy;

    private Date createTime;

    private Date updateBy;

    private Date updateTime;

    private List<SystemPermission> permissionList;


}
