package com.zhang.kdzs.system.shiro;

import com.zhang.kdzs.system.entity.Menu;
import com.zhang.kdzs.system.entity.Role;
import com.zhang.kdzs.system.entity.User;
import com.zhang.kdzs.system.service.MenuService;
import com.zhang.kdzs.system.service.RoleService;
import com.zhang.kdzs.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhang
 * @date 2019年8月3日
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @Autowired
    private MenuService menuService;

    /**
     * 权限验证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //获取用户角色
        List<Role> roleList = roleService.findUserRole(user.getUsername());
        Set<String> roleSet = roleList.stream().map(Role::getName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roleSet);

        //获取用户权限
        List<Menu> menuList = menuService.findUserMenus(user.getUsername());
        Set<String> permSet = menuList.stream().map(Menu::getPermission).collect(Collectors.toSet());
        simpleAuthorizationInfo.setStringPermissions(permSet);

        return simpleAuthorizationInfo;


    }

    /**
     * 身份验证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        User user = userService.findByName(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());

        return authenticationInfo;
    }

}
