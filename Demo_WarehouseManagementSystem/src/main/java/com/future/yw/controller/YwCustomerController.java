package com.future.yw.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.Constast;
import com.future.wms.common.DataGridView;
import com.future.wms.common.ResultObj;
import com.future.yw.model.entity.YwCustomer;
import com.future.yw.model.vo.YwCustomerVo;
import com.future.yw.service.IYwCustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@RestController
@RequestMapping("yw/customer")
public class YwCustomerController {

    @Autowired
    private IYwCustomerService iYwCustomerService;

    /**
     * 查询所有的客户
     * @param customerVo
     * @return
     */
    @RequestMapping("/loadAllCustomer")
    public DataGridView loadAllCustomer(YwCustomerVo customerVo) {
        //1.声明一个分页page对象
        IPage<YwCustomer> page = new Page<YwCustomer>(customerVo.getPage(), customerVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<YwCustomer> queryWrapper = new QueryWrapper<YwCustomer>();
        queryWrapper.like(StringUtils.isNotBlank(customerVo.getCustomername()), "customername", customerVo.getCustomername());
        queryWrapper.like(StringUtils.isNotBlank(customerVo.getConnectionpersion()), "connectionpersion", customerVo.getConnectionpersion());
        queryWrapper.like(StringUtils.isNotBlank(customerVo.getPhone()), "phone", customerVo.getPhone());
        iYwCustomerService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加一个客户
     * @param customerVo
     * @return
     */
    @RequestMapping("/addCustomer")
    public ResultObj addCustomer(YwCustomerVo customerVo) {
        try {
            iYwCustomerService.save(customerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改一个客户
     * @param customerVo
     * @return
     */
    @RequestMapping("/updateCustomer")
    public ResultObj updateCustomer(YwCustomerVo customerVo) {
        try {
            iYwCustomerService.updateById(customerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除一个客户
     * @param id 客户的ID
     * @return
     */
    @RequestMapping("/deleteCustomer")
    public ResultObj deleteCustomer(Integer id) {
        try {
            iYwCustomerService.deleteCustomerById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载所有客户的下拉列表
     * @return
     */
    @RequestMapping("/loadAllCustomerForSelect")
    public DataGridView loadAllCustomerForSelect() {
        QueryWrapper<YwCustomer> queryWrapper = new QueryWrapper<YwCustomer>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<YwCustomer> list = iYwCustomerService.list(queryWrapper);
        return new DataGridView(list);
    }

}

