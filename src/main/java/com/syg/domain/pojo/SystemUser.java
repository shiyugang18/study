package com.syg.domain.pojo;

import lombok.Data;

import javax.management.relation.Role;
import java.util.Date;
import java.util.List;

/**
 * @Author: shiyugang
 * @Date: 2019/12/8 14:33
 */
@Data
public class SystemUser {

    private String id;

    private String userName;

    private String password;

    private String salt;

    private Integer isEnable;

    private String createBy;

    private Date createTime;

    private Date updateBy;

    private Date updateTime;

    private List<SystemRole> roleList;

}
