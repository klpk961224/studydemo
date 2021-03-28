package com.future.wms.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.future.wms.common.ActiveUser;
import com.future.wms.common.Constast;
import com.future.wms.model.entity.SysPermission;
import com.future.wms.model.entity.SysUser;
import com.future.wms.service.ISysPermissionService;
import com.future.wms.service.ISysRoleService;
import com.future.wms.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description [用户数据连接]
 * @author evanliu
 * @create 2021-03-24 17:00
 */
public class UserRealm extends AuthorizingRealm {

    /**
     * @Lazy 当需要使用的时候，才加载。  即：当CacheAspect被解析之后，userService才会解析，要不然切面会不生效
     */
    @Autowired
    @Lazy
    private ISysUserService iSysUserService;

    @Autowired
    @Lazy
    private ISysPermissionService permissionService;

    @Autowired
    @Lazy
    private ISysRoleService roleService;

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
            //根据用户ID查询percode
            QueryWrapper<SysPermission> qw = new QueryWrapper<>();
            //设置只能查询所有可用的菜单
            qw.eq("type", Constast.TYPE_PERMISSION);
            qw.eq("available", Constast.AVAILABLE_TRUE);
            Integer userId = sysUser.getId();
            //根据用户ID查询角色ID，因为一个用户可能有多个角色，所以使用list进行存储
            List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(userId);
            //声明一个Set对象pids用来存储查询出来的权限，使用Set可以过滤重复的权限
            Set<Integer> pids = new HashSet<>();
            for (Integer rid : currentUserRoleIds) {
                //根据角色ID查询出权限ID
                List<Integer> permissionIds = roleService.queryRolePermissionIdsByRid(rid);
                pids.addAll(permissionIds);
            }
            List<SysPermission> list = new ArrayList<>();
            if (pids.size() > 0) {
                qw.in("id", pids);
                list = permissionService.list(qw);
            }
            List<String> percodes = new ArrayList<>();
            for (SysPermission permission : list) {
                percodes.add(permission.getPercode());
            }
            //放到activeUser
            activeUser.setPermissions(percodes);

            //生成盐
            ByteSource credentialsSalt = ByteSource.Util.bytes(sysUser.getSalt());
            /**
             * 参数说明：
             * 参数1：活动的User
             * 参数2：从数据库里面查询出来的密码(已经通过MD5加密)
             * 参数3：从数据库里面查询出来的盐
             * 参数4：当前类名
             */
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, sysUser.getPwd(), credentialsSalt, this.getName());
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
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ActiveUser activeUser = (ActiveUser) principalCollection.getPrimaryPrincipal();
        SysUser user = activeUser.getSysUser();
        List<String> superPermission = new ArrayList<>();
        superPermission.add("*:*");
        List<String> permissions = activeUser.getPermissions();
        if (user.getType().equals(Constast.USER_TYPE_SUPER)) {
            authorizationInfo.addStringPermissions(superPermission);
        } else {
            if (null != permissions && permissions.size() > 0) {
                authorizationInfo.addStringPermissions(permissions);
            }
        }
        return authorizationInfo;
    }
}
