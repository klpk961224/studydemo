package com.future.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.DataGridView;
import com.future.wms.common.ResultObj;
import com.future.wms.common.TreeNode;
import com.future.wms.model.entity.SysDept;
import com.future.wms.model.vo.SysDeptVo;
import com.future.wms.service.ISysDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController {

    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 加载部门左边的菜单树
     * @param deptVo
     * @return
     */
    @RequestMapping("/loadDeptManagerLeftTreeJson")
    public DataGridView loadManagerLeftTreeJson(SysDeptVo deptVo) {
        //查询出所有的部门，存放进list中
        List<SysDept> list = sysDeptService.list();

        List<TreeNode> treeNodes = new ArrayList<>();
        //将部门放入treeNodes中，组装成json
        for (SysDept dept : list) {
            Boolean open = dept.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(dept.getId(), dept.getPid(), dept.getName(), open));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询所有部门数据
     * @param deptVo
     * @return
     */
    @RequestMapping("/loadAllDept")
    public DataGridView loadAllDept(SysDeptVo deptVo) {
        IPage<SysDept> page = new Page<>(deptVo.getPage(), deptVo.getLimit());
        //进行模糊查询
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getName()), "name", deptVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getRemark()), "remark", deptVo.getRemark());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getAddress()), "address", deptVo.getAddress());
        queryWrapper.eq(deptVo.getId() != null, "id", deptVo.getId()).or().eq(deptVo.getId() != null, "pid", deptVo.getId());
        queryWrapper.orderByAsc("ordernum");
        //进行查询
        sysDeptService.page(page, queryWrapper);
        //返回DataGridView
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加部门
     * @param deptVo
     * @return
     */
    @RequestMapping("/addDept")
    public ResultObj addDept(SysDeptVo deptVo) {
        try {
            deptVo.setCreatetime(new Date());
            sysDeptService.save(deptVo);
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
    @RequestMapping("/loadDeptMaxOrderNum")
    public Map<String, Object> loadDeptMaxOrderNum() {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<SysDept> page = new Page<>(1, 1);
        List<SysDept> list = sysDeptService.page(page, queryWrapper).getRecords();
        if (list.size() > 0) {
            map.put("value", list.get(0).getOrdernum() + 1);
        } else {
            map.put("value", 1);
        }
        return map;
    }

    /**
     * 更新部门
     * @param deptVo
     * @return
     */
    @RequestMapping("/updateDept")
    public ResultObj updateDept(SysDeptVo deptVo) {
        try {
            sysDeptService.updateById(deptVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 检查当前部门是否有子部门
     * @param deptVo
     * @return
     */
    @RequestMapping("/checkDeptHasChildrenNode")
    public Map<String, Object> checkDeptHasChildrenNode(SysDeptVo deptVo) {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", deptVo.getId());
        List<SysDept> list = sysDeptService.list(queryWrapper);
        if (list.size() > 0) {
            map.put("value", true);
        } else {
            map.put("value", false);
        }
        return map;
    }

    /**
     * 删除部门
     * @param deptVo
     * @return
     */
    @RequestMapping("/deleteDept")
    public ResultObj deleteDept(SysDeptVo deptVo) {
        try {
            sysDeptService.removeById(deptVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

