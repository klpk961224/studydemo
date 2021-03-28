package com.future.wms.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.*;
import com.future.wms.model.entity.SysDept;
import com.future.wms.model.entity.SysRole;
import com.future.wms.model.entity.SysUser;
import com.future.wms.model.vo.SysUserVo;
import com.future.wms.service.ISysDeptService;
import com.future.wms.service.ISysRoleService;
import com.future.wms.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ISysDeptService iSysDeptService;

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 查询所有用户
     * @param userVo
     * @return
     */
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(SysUserVo userVo) {
        IPage<SysUser> page = new Page<SysUser>(userVo.getPage(), userVo.getLimit());
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        //根据用户登录名称以及用户名称模糊查询用户
        queryWrapper.like(StringUtils.isNotBlank(userVo.getName()), "loginname", userVo.getName()).or().eq(StringUtils.isNotBlank(userVo.getName()), "name", userVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(userVo.getAddress()), "address", userVo.getAddress());
        //查询系统用户
        queryWrapper.eq("type", Constast.USER_TYPE_NORMAL);
        queryWrapper.eq(userVo.getDeptid() != null, "deptid", userVo.getDeptid());
        queryWrapper.orderByDesc("id");
        iSysUserService.page(page, queryWrapper);

        //将所有用户数据放入list中
        List<SysUser> list = page.getRecords();
        for (SysUser user : list) {
            Integer deptId = user.getDeptid();
            if (deptId != null) {
                //先从缓存中去取，如果缓存中没有就去数据库中取
                SysDept one = iSysDeptService.getById(deptId);
                //设置user的部门名称
                user.setDeptname(one.getName());
            }
            Integer mgr = user.getMgr();
            if (mgr != null && mgr != 0) {
                SysUser one = iSysUserService.getById(mgr);
                //设置user的领导名称
                user.setLeadername(one.getName());
            }
        }
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 加载排序码
     * @return
     */
    @RequestMapping("loadUserMaxOrderNum")
    public Map<String, Object> loadUserMaxOrderNum() {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        queryWrapper.orderByDesc("ordernum");
        IPage<SysUser> page = new Page<>(1, 1);
        List<SysUser> list = iSysUserService.page(page, queryWrapper).getRecords();
        if (list.size() > 0) {
            map.put("value", list.get(0).getOrdernum() + 1);
        } else {
            map.put("value", 1);
        }
        return map;
    }

    /**
     * 根据部门ID查询用户
     * @param deptId
     * @return
     */
    @RequestMapping("loadUsersByDeptId")
    public DataGridView loadUsersByDeptIp(Integer deptId) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        queryWrapper.eq(deptId != null, "deptid", deptId);
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        queryWrapper.eq("type", Constast.USER_TYPE_NORMAL);
        List<SysUser> list = iSysUserService.list(queryWrapper);
        for (SysUser user : list) {
            System.out.println(user.toString());
        }
        return new DataGridView(list);
    }

    /**
     * 把用户名转成拼音
     * @param username
     * @return
     */
    @RequestMapping("changeChineseToPinyin")
    public Map<String, Object> changeChineseToPinyin(String username) {
        Map<String, Object> map = new HashMap<String, Object>(16);
        if (null != username) {
            map.put("value", PinyinUtils.getPingYin(username));
        } else {
            map.put("value", "");
        }
        return map;
    }

    /**
     * 添加用户
     * @param userVo
     * @return
     */
    @RequestMapping("addUser")
    public ResultObj addUser(SysUserVo userVo) {
        try {
            //设置类型
            userVo.setType(Constast.USER_TYPE_NORMAL);
            //设置盐
            String salt = IdUtil.simpleUUID().toUpperCase();
            userVo.setSalt(salt);
            //设置默认密码
            userVo.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 2).toString());
            //设置用户默认头像
            userVo.setImgpath(Constast.DEFAULT_IMG_USER);
            iSysUserService.save(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据id查询一个用户
     * @param id  领导的id
     * @return
     */
    @RequestMapping("loadUserById")
    public DataGridView loadUserById(Integer id) {
        return new DataGridView(iSysUserService.getById(id));
    }

    /**
     * 修改用户
     * @param userVo
     * @return
     */
    @RequestMapping("updateUser")
    public ResultObj updateUser(SysUserVo userVo) {
        try {
            iSysUserService.updateById(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("deleteUser/{id}")
    public ResultObj deleteUser(@PathVariable("id") Integer id) {
        try {
            iSysUserService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据用户ID查询当前用户是否是其他用户的直属领导
     * @param userId
     * @return
     */
    @RequestMapping("queryMgrByUserId")
    public ResultObj queryMgrByUserId(Integer userId) {
        Boolean isMgr = iSysUserService.queryMgrByUserId(userId);
        if (isMgr) {
            return ResultObj.DELETE_ERROR_NEWS;
        } else {
            return ResultObj.DELETE_QUERY;
        }
    }

    /**
     * 重置用户密码
     * @param id
     * @return
     */
    @RequestMapping("resetPwd/{id}")
    public ResultObj resetPwd(@PathVariable("id") Integer id) {
        try {
            SysUser user = new SysUser();
            user.setId(id);
            //设置盐  32位(大写英文字母(A-Z)加数字(0-9))
            String salt = IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);
            //设置密码
            user.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 2).toString());
            iSysUserService.updateById(user);
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 根据用户id查询角色并选中已拥有的角色
     * @param id 用户id
     * @return
     */
    @RequestMapping("initRoleByUserId")
    public DataGridView initRoleByUserId(Integer id) {
        //1.查询所有可用的角色
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Map<String, Object>> listMaps = sysRoleService.listMaps(queryWrapper);
        //2.查询当前用户拥有的角色ID集合
        List<Integer> currentUserRoleIds = sysRoleService.queryUserRoleIdsByUid(id);
        for (Map<String, Object> map : listMaps) {
            Boolean LAY_CHECKED = false;
            Integer roleId = (Integer) map.get("id");
            for (Integer rid : currentUserRoleIds) {
                //如果当前用户已有该角色，则让LAY_CHECKED为true。LAY_CHECKED为true时，复选框选中
                if (rid.equals(roleId)) {
                    LAY_CHECKED = true;
                    break;
                }
            }
            map.put("LAY_CHECKED", LAY_CHECKED);
        }
        return new DataGridView(Long.valueOf(listMaps.size()), listMaps);
    }

    /**
     * 保存用户和角色的关系
     * @param uid 用户的ID
     * @param ids 用户拥有的角色的ID的数组
     * @return
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(Integer uid, Integer[] ids) {
        try {
            iSysUserService.saveUserRole(uid, ids);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

    /**
     * 修改用户的密码
     * @param oldPassword  用户的原密码
     * @param newPwdOne     用户第一次输入的新密码
     * @param newPwdTwo     用户第二次输入的新密码
     * @return
     */
    @RequestMapping("changePassword")
    public ResultObj changePassword(String oldPassword, String newPwdOne, String newPwdTwo) {
        //1.先通过session获得当前用户的ID
        SysUser user = (SysUser) WebUtils.getSession().getAttribute("user");
        //2.将oldPassword加盐并散列两次在和数据库中的密码进行对比
        Integer userId = user.getId();
        SysUser user1 = iSysUserService.getById(userId);
        //2.1获得该用户的盐
        String salt = user1.getSalt();
        //2.2通过用户输入的原密码，从数据库中查出的盐，散列次数生成新的旧密码
        String oldPassword2 = new Md5Hash(oldPassword, salt, Constast.HASHITERATIONS).toString();
        if (oldPassword2.equals(user1.getPwd())) {
            if (newPwdOne.equals(newPwdTwo)) {
                //3.生成新的密码
                String newPassword = new Md5Hash(newPwdOne, salt, Constast.HASHITERATIONS).toString();
                user1.setPwd(newPassword);
                iSysUserService.updateById(user1);
                return ResultObj.UPDATE_SUCCESS;
            } else {
                return ResultObj.UPDATE_ERROR;
            }
        } else {
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 返回当前已登录的user
     * @return
     */
    @RequestMapping("getNowUser")
    public SysUser getNowUser() {
        //1.获取当前session中的user
        SysUser user = (SysUser) WebUtils.getSession().getAttribute("user");
        System.out.println("*****************************************");
        System.out.println(user);
        return user;
    }


    /**
     * 修改用户
     * @param userVo
     * @return
     */
    @RequestMapping("updateUserInfo")
    public ResultObj updateUserInfo(SysUserVo userVo) {
        try {
            //用户头像不是默认图片
            if (!(userVo.getImgpath() != null && userVo.getImgpath().equals(Constast.DEFAULT_IMG_GOODS))) {
                if (userVo.getImgpath().endsWith("_temp")) {
                    String newName = AppFileUtils.renameFile(userVo.getImgpath());
                    userVo.setImgpath(newName);
                    //删除原先的图片
                    String oldPath = iSysUserService.getById(userVo.getId()).getImgpath();
                    AppFileUtils.removeFileByPath(oldPath);
                    //获取存储在session中的user并重新设置user中的图片地址
                    SysUser user = (SysUser) WebUtils.getSession().getAttribute("user");
                    user.setImgpath(newName);
                    //重新设置user
                    WebUtils.getSession().setAttribute("user", user);
                }
            }
            iSysUserService.updateById(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}