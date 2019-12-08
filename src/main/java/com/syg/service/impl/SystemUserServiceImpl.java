package com.syg.service.impl;

import com.syg.domain.pojo.SystemPermission;
import com.syg.domain.pojo.SystemRole;
import com.syg.domain.pojo.SystemUser;
import com.syg.service.SystemUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: shiyugang
 * @Date: 2019/12/8 15:12
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Override
    public List<SystemRole> getRoleList(String id) {
        return null;
    }

    @Override
    public List<SystemPermission> getPermissionList(String id) {
        return null;
    }

    @Override
    public SystemUser findByUserName(String userName) {
        return null;
    }

}
