package com.syg.shiro;

import com.syg.domain.pojo.SystemPermission;
import com.syg.domain.pojo.SystemRole;
import com.syg.domain.pojo.SystemUser;
import com.syg.service.impl.SystemUserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.Role;
import java.util.List;

/**
 * 自定义的Realm
 * @Author: shiyugang
 * @Date: 2019/12/8 14:15
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SystemUserServiceImpl systemUserService;

    /**
     * 授权
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("授权配置 --> MyRealm.doGetAuthorizationInfo() ");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SystemUser systemUser  = (SystemUser)principalCollection.getPrimaryPrincipal();
        String id = systemUser.getId();

        List<SystemRole> roles = systemUserService.getRoleList(id);
        systemUser.setRoleList(roles);
        for(SystemRole systemRole:systemUser.getRoleList()){
            authorizationInfo.addRole(systemRole.getRole());
            List<SystemPermission> permissionListr = systemUserService.getPermissionList(systemRole.getId());
            systemRole.setPermissionList(permissionListr);
            for(SystemPermission systemPermission:systemRole.getPermissionList()){
                authorizationInfo.addStringPermission(systemPermission.getPermission());
            }
        }

        return authorizationInfo;

    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("认证配置 --> MyRealm.doGetAuthenticationInfo()");

        //获取用户的输入的账号.
        String username = (String)authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SystemUser systemUser = systemUserService.findByUserName(username);

        System.out.println("----->>userInfo="+systemUser.getPassword());
        if(systemUser == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                systemUser,
                //密码
                systemUser.getPassword(),
                //salt=username+salt
                ByteSource.Util.bytes(systemUser.getSalt()),
                //realm name
                getName()
        );
        return authenticationInfo;

    }

}
