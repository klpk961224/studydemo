package com.future.yw.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.DataGridView;
import com.future.wms.common.ResultObj;
import com.future.yw.model.entity.YwCustomer;
import com.future.yw.model.entity.YwGoods;
import com.future.yw.model.entity.YwSalesback;
import com.future.yw.model.vo.YwSalesbackVo;
import com.future.yw.service.IYwCustomerService;
import com.future.yw.service.IYwGoodsService;
import com.future.yw.service.IYwSalesbackService;
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
@RequestMapping("yw/salesback")
public class YwSalesbackController {

    @Autowired
    private IYwSalesbackService iYwSalesbackService;

    @Autowired
    private IYwCustomerService iYwCustomerService;

    @Autowired
    private IYwGoodsService iYwGoodsService;

    /**
     * 添加退货信息
     * @param id    进货单ID
     * @param number    退货数量
     * @param remark    备注
     * @return
     */
    @RequestMapping("/addSalesback")
    public ResultObj addSalesback(Integer id, Integer number, String remark) {
        try {
            iYwSalesbackService.addSalesback(id, number, remark);
            return ResultObj.BACKINPORT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.BACKINPORT_ERROR;
        }
    }

    /**
     * 查询商品销售退货
     * @param salesbackVo
     * @return
     */
    @RequestMapping("/loadAllSalesback")
    public DataGridView loadAllSalesback(YwSalesbackVo salesbackVo) {
        IPage<YwSalesback> page = new Page<YwSalesback>(salesbackVo.getPage(), salesbackVo.getLimit());
        QueryWrapper<YwSalesback> queryWrapper = new QueryWrapper<YwSalesback>();
        //对客户进行查询
        queryWrapper.eq(salesbackVo.getCustomerid() != null && salesbackVo.getCustomerid() != 0, "customerid", salesbackVo.getCustomerid());
        //对商品进行查询
        queryWrapper.eq(salesbackVo.getGoodsid() != null && salesbackVo.getGoodsid() != 0, "goodsid", salesbackVo.getGoodsid());
        //对时间进行查询要求大于开始时间小于结束时间
        queryWrapper.ge(salesbackVo.getStartTime() != null, "salesbacktime", salesbackVo.getStartTime());
        queryWrapper.le(salesbackVo.getEndTime() != null, "salesbacktime", salesbackVo.getEndTime());
        //通过商品退货时间对商品进行排序
        queryWrapper.orderByDesc("salesbacktime");
        iYwSalesbackService.page(page, queryWrapper);
        List<YwSalesback> records = page.getRecords();
        for (YwSalesback salesback : records) {
            System.out.println("============================");
            YwCustomer customer = iYwCustomerService.getById(salesback.getCustomerid());
            if (customer != null) {
                //设置客户姓名
                salesback.setCustomername(customer.getCustomername());
            }
            YwGoods goods = iYwGoodsService.getById(salesback.getGoodsid());
            if (goods != null) {
                //设置商品名称
                salesback.setGoodsname(goods.getGoodsname());
                //设置商品规格
                salesback.setSize(goods.getSize());
            }
        }
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 删除商品销售退回信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteSalesback")
    public ResultObj deleteSalesback(Integer id) {
        try {
            iYwSalesbackService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

