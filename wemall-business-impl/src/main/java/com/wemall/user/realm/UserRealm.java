package com.wemall.user.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wemall.permission.entity.Permission;
import com.wemall.role.entity.Role;
import com.wemall.user.entity.User;
import com.wemall.user.service.IUserService;

@Component(value="myRealm")
public class UserRealm extends AuthorizingRealm {
	@Autowired
	private IUserService userService;
    /**
     * 提供用户信息返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = userService.selectByAccount(username);
        //userService.selectByPrimaryKey();
        // 根据用户名查询当前用户拥有的角色
        Set<Role> roles = user.getRoles();
        Set<String> roleNames = new HashSet<String>();
        Set<String> permissionNames = new HashSet<String>();
        for (Role role : roles) {
            roleNames.add(role.getRoleKey());
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
            	permissionNames.add(permission.getPermissionKey()); 
            }
        }
        // 将角色名称提供给info
        authorizationInfo.setRoles(roleNames);
        // 根据用户名查询当前用户权限
        authorizationInfo.setStringPermissions(permissionNames);
        return authorizationInfo;
    }

    /**
     * 提供账户信息返回认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.selectByAccount(username);
        if (user == null) {
            // 用户名不存在抛出异常
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserAccount(),
               user.getPassword(), getName());
        return authenticationInfo;
    }
}