package com.future.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.Constast;
import com.future.wms.common.DataGridView;
import com.future.wms.common.ResultObj;
import com.future.wms.common.TreeNode;
import com.future.wms.model.entity.SysPermission;
import com.future.wms.model.vo.SysPermissionVo;
import com.future.wms.service.ISysPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/permission")
public class SysPermissionController {


    @Autowired
    private ISysPermissionService sysPermissionService;

    /**
     * 加载权限左边的权限树
     * @param permissionVo
     * @return
     */
    @RequestMapping("/loadPermissionManagerLeftTreeJson")
    public DataGridView loadPermissionManagerLeftTreeJson(SysPermissionVo permissionVo) {
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<SysPermission>();
        queryWrapper.eq("type", Constast.TYPE_MENU);
        //查询出所有的权限，存放进list中
        List<SysPermission> list = sysPermissionService.list(queryWrapper);
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        //将权限放入treeNodes中，组装成json
        for (SysPermission permission : list) {
            Boolean open = permission.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(permission.getId(), permission.getPid(), permission.getTitle(), open));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询所有权限数据
     * @param permissionVo
     * @return
     */
    @RequestMapping("/loadAllPermission")
    public DataGridView loadAllPermission(SysPermissionVo permissionVo) {
        IPage<SysPermission> page = new Page<>(permissionVo.getPage(), permissionVo.getLimit());
        //进行模糊查询
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        //只能查询权限
        queryWrapper.eq("type", Constast.TYPE_PERMISSION);
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()), "title", permissionVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getPercode()), "percode", permissionVo.getPercode());
        queryWrapper.eq(permissionVo.getId() != null, "pid", permissionVo.getId());
        queryWrapper.orderByAsc("ordernum");
        //进行查询
        sysPermissionService.page(page, queryWrapper);
        //返回DataGridView
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加权限
     * @param permissionVo
     * @return
     */
    @RequestMapping("/addPermission")
    public ResultObj addPermission(SysPermissionVo permissionVo) {
        try {
            //设置添加类型为 permission
            permissionVo.setType(Constast.TYPE_PERMISSION);
            sysPermissionService.save(permissionVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 加载排序码
     * @return
     */
    @RequestMapping("/loadPermissionMaxOrderNum")
    public Map<String, Object> loadPermissionMaxOrderNum() {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<SysPermission> page = new Page<>(1, 1);
        List<SysPermission> list = sysPermissionService.page(page, queryWrapper).getRecords();
        if (list.size() > 0) {
            map.put("value", list.get(0).getOrdernum() + 1);
        } else {
            map.put("value", 1);
        }
        return map;
    }

    /**
     * 更新权限
     * @param permissionVo
     * @return
     */
    @RequestMapping("/updatePermission")
    public ResultObj updatePermission(SysPermissionVo permissionVo) {
        try {
            sysPermissionService.updateById(permissionVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 检查当前权限是否有子权限
     * @param permissionVo
     * @return
     */
    @RequestMapping("/checkPermissionHasChildrenNode")
    public Map<String, Object> checkPermissionHasChildrenNode(SysPermissionVo permissionVo) {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", permissionVo.getId());
        List<SysPermission> list = sysPermissionService.list(queryWrapper);
        if (list.size() > 0) {
            map.put("value", true);
        } else {
            map.put("value", false);
        }
        return map;
    }

    /**
     * 删除权限
     * @param permissionVo
     * @return
     */
    @RequestMapping("/deletePermission")
    public ResultObj deletePermission(SysPermissionVo permissionVo) {
        try {
            sysPermissionService.removeById(permissionVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

