package com.syg.service;

import com.syg.domain.pojo.SystemPermission;
import com.syg.domain.pojo.SystemRole;
import com.syg.domain.pojo.SystemUser;

import java.util.List;

/**
 * @Author: shiyugang
 * @Date: 2019/12/8 15:12
 */
public interface SystemUserService {

    List<SystemRole> getRoleList(String id);

    List<SystemPermission> getPermissionList(String id);

    SystemUser findByUserName(String userName);

}
