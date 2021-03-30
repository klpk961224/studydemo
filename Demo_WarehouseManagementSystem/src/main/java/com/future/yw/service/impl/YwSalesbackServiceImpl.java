package com.future.yw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.wms.common.WebUtils;
import com.future.wms.model.entity.SysUser;
import com.future.yw.mapper.YwGoodsMapper;
import com.future.yw.mapper.YwSalesMapper;
import com.future.yw.mapper.YwSalesbackMapper;
import com.future.yw.model.entity.YwGoods;
import com.future.yw.model.entity.YwSales;
import com.future.yw.model.entity.YwSalesback;
import com.future.yw.service.IYwSalesbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@Service
public class YwSalesbackServiceImpl extends ServiceImpl<YwSalesbackMapper, YwSalesback> implements IYwSalesbackService {

    @Autowired
    private YwSalesMapper ywSalesMapper;

    @Autowired
    private YwGoodsMapper ywGoodsMapper;

    /**
     * @param id    销售单ID
     * @param number    退货数量
     * @param remark    备注
     */
    @Override
    public void addSalesback(Integer id, Integer number, String remark) {
        //1.通过销售单ID查询出销售单信息
        YwSales sales = ywSalesMapper.selectById(id);
        //2.根据商品ID查询商品信息
        YwGoods goods = ywGoodsMapper.selectById(sales.getGoodsid());
        //3.修改商品的数量     商品的数量-退货的数量
        goods.setNumber(goods.getNumber() + number);

        //修改进货的数量
        sales.setNumber(sales.getNumber() - number);
        ywSalesMapper.updateById(sales);

        //4.进行修改
        ywGoodsMapper.updateById(goods);

        //5.添加退货单信息
        YwSalesback salesback = new YwSalesback();
        salesback.setGoodsid(sales.getGoodsid());

        salesback.setNumber(number);
        SysUser user = (SysUser) WebUtils.getSession().getAttribute("user");
        salesback.setOperateperson(user.getName());


        salesback.setSalebackprice(sales.getSaleprice());
        salesback.setPaytype(sales.getPaytype());

        salesback.setSalesbacktime(new Date());
        salesback.setRemark(remark);


        salesback.setCustomerid(sales.getCustomerid());


        getBaseMapper().insert(salesback);
    }


}
