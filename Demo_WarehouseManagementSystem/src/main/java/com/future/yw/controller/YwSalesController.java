package com.future.yw.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.DataGridView;
import com.future.wms.common.ResultObj;
import com.future.wms.common.WebUtils;
import com.future.wms.model.entity.SysUser;
import com.future.yw.model.entity.YwCustomer;
import com.future.yw.model.entity.YwGoods;
import com.future.yw.model.entity.YwSales;
import com.future.yw.model.vo.YwSalesVo;
import com.future.yw.service.IYwCustomerService;
import com.future.yw.service.IYwGoodsService;
import com.future.yw.service.IYwSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
@RequestMapping("yw/sales")
public class YwSalesController {

    @Autowired
    private IYwSalesService iYwSalesService;

    @Autowired
    private IYwCustomerService iYwCustomerService;

    @Autowired
    private IYwGoodsService iYwGoodsService;

    /**
     * 查询所有商品销售信息
     * @param salesVo
     * @return
     */
    @RequestMapping("/loadAllSales")
    public DataGridView loadAllSales(YwSalesVo salesVo) {
        IPage<YwSales> page = new Page<YwSales>(salesVo.getPage(), salesVo.getLimit());
        QueryWrapper<YwSales> queryWrapper = new QueryWrapper<YwSales>();
        //根据客户进行模糊查询
        queryWrapper.eq(salesVo.getCustomerid() != null && salesVo.getCustomerid() != 0, "customerid", salesVo.getCustomerid());
        //根据商品模糊查询
        queryWrapper.eq(salesVo.getGoodsid() != null && salesVo.getGoodsid() != 0, "goodsid", salesVo.getGoodsid());
        //根据时间进行模糊查询
        queryWrapper.ge(salesVo.getStartTime() != null, "salestime", salesVo.getStartTime());
        queryWrapper.le(salesVo.getEndTime() != null, "salestime", salesVo.getEndTime());
        IPage<YwSales> page1 = iYwSalesService.page(page, queryWrapper);
        List<YwSales> records = page1.getRecords();
        for (YwSales sales : records) {
            //设置客户姓名
            YwCustomer customer = iYwCustomerService.getById(sales.getCustomerid());
            if (null != customer) {
                sales.setCustomername(customer.getCustomername());
            }
            //设置商品名称
            YwGoods goods = iYwGoodsService.getById(sales.getGoodsid());
            if (null != goods) {
                //设置商品名称
                sales.setGoodsname(goods.getGoodsname());
                //设置商品规格
                sales.setSize(goods.getSize());
            }
        }
        return new DataGridView(page1.getTotal(), page1.getRecords());
    }

    /**
     * 添加商品销售信息
     * @param salesVo
     * @return
     */
    @RequestMapping("/addSales")
    public ResultObj addSales(YwSalesVo salesVo) {
        try {
            //获得当前系统用户
            SysUser user = (SysUser) WebUtils.getSession().getAttribute("user");
            //设置操作人
            salesVo.setOperateperson(user.getName());
            //设置销售时间
            salesVo.setSalestime(new Date());
            iYwSalesService.save(salesVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新商品销售信息
     * @param salesVo
     * @return
     */
    @RequestMapping("/updateSales")
    public ResultObj updateSales(YwSalesVo salesVo) {
        try {
            iYwSalesService.updateById(salesVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除商品销售信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteSales")
    public ResultObj deleteSales(Integer id) {
        try {
            iYwSalesService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

