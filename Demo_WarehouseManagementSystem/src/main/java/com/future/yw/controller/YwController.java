package com.future.yw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author evanliu
 * @create 2021-03-30 18:28
 */
@Controller
@RequestMapping("yw")
public class YwController {

    /**
     * 跳转到客户管理页面
     * @return
     */
    @RequestMapping("/toCustomerManager")
    public String toCustomerManager() {
        return "yw/customer/customerManager";
    }

    /**
     * 跳转到供应商管理页面
     * @return
     */
    @RequestMapping("/toProviderManager")
    public String toProviderManager() {
        return "yw/provider/providerManager";
    }

    /**
     * 跳转到商品管理页面
     * @return
     */
    @RequestMapping("/toGoodsManager")
    public String toGoodsManager() {
        return "yw/goods/goodsManager";
    }

    /**
     * 跳转到进货管理页面
     * @return
     */
    @RequestMapping("/toImportManager")
    public String toImportManager() {
        return "yw/import/importManager";
    }

    /**
     * 跳转到退货管理页面
     * @return
     */
    @RequestMapping("/toOutportManager")
    public String toOutportManager() {
        return "yw/outport/outportManager";
    }

    /**
     * 跳转到商品销售管理页面
     * @return
     */
    @RequestMapping("/toSalesManager")
    public String toSalesManager() {
        return "yw/sales/salesManager";
    }

    /**
     * 跳转到商品销售管理页面
     * @return
     */
    @RequestMapping("/toSalesbackManager")
    public String toSalesbackManager() {
        return "yw/salesback/salesbackManager";
    }
}
