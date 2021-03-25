package com.future.wms.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.future.wms.common.ActiveUser;
import com.future.wms.model.entity.SysUser;
import com.future.wms.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * @Description [用户数据连接]
 * @author evanliu
 * @create 2021-03-24 17:00
 */
public class UserRealm extends AuthorizingRealm {

    /**
     * 当需要使用的时候，才加载。  即：当CacheAspect被解析之后，userService才会解析，要不然切面会不生效
     */
    @Autowired
    @Lazy
    private ISysUserService iSysUserService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * @Description [认证]
     * @Author evanliu
     * @Date 2021-03-24 13:36
     * @param: authenticationToken ->
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.通过登录名在数据库中找到对应的用户信息
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname", authenticationToken.getPrincipal().toString());
        SysUser sysUser = iSysUserService.getOne(queryWrapper);

        //1.若用户不行存在，可以抛出UnknownAccountException
        if (sysUser == null) {
            throw new UnknownAccountException("用户不存在");
        }

        //2.若用户不可用被锁定，可以抛出LockedAccountException
        if (sysUser.getAvailable() == 0) {
            throw new LockedAccountException("用户不可用");
        }

        // 3.若用户存在且可用
        if (null != sysUser) {
            ActiveUser activeUser = new ActiveUser();
            activeUser.setSysUser(sysUser);

            ByteSource credentialSalt = ByteSource.Util.bytes(sysUser.getSalt());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, sysUser.getPwd(), credentialSalt, this.getName());
            return info;
        }

        return null;
    }

    /**
     * @Description [授权]
     * @Author evanliu
     * @Date 2021-03-24 16:45
     * @param: principalCollection ->
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
