package com.future.yw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.wms.common.WebUtils;
import com.future.wms.model.entity.SysUser;
import com.future.yw.mapper.YwGoodsMapper;
import com.future.yw.mapper.YwImportMapper;
import com.future.yw.mapper.YwOutportMapper;
import com.future.yw.model.entity.YwGoods;
import com.future.yw.model.entity.YwImport;
import com.future.yw.model.entity.YwOutport;
import com.future.yw.service.IYwOutportService;
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
public class YwOutportServiceImpl extends ServiceImpl<YwOutportMapper, YwOutport> implements IYwOutportService {

    @Autowired
    private YwImportMapper ywImportMapper;

    @Autowired
    private YwGoodsMapper ywGoodsMapper;

    /**
     * @param id    进货单ID
     * @param number    退货数量
     * @param remark    备注
     */
    @Override
    public void addOutport(Integer id, Integer number, String remark) {
        //1.通过进货单ID查询出进货单信息
        YwImport ywImport = ywImportMapper.selectById(id);
        //2.根据商品ID查询商品信息
        YwGoods goods = ywGoodsMapper.selectById(ywImport.getGoodsid());
        //3.修改商品的数量     商品的数量-退货的数量
        goods.setNumber(goods.getNumber() - number);

        //修改进货的数量
        ywImport.setNumber(ywImport.getNumber() - number);
        ywImportMapper.updateById(ywImport);

        //4.进行修改
        ywGoodsMapper.updateById(goods);

        //5.添加退货单信息
        YwOutport outport = new YwOutport();
        outport.setGoodsid(ywImport.getGoodsid());
        outport.setNumber(number);
        SysUser user = (SysUser) WebUtils.getSession().getAttribute("user");
        outport.setOperateperson(user.getName());

        outport.setOutportprice(ywImport.getImportprice());

        outport.setPaytype(ywImport.getPaytype());
        outport.setOutputtime(new Date());
        outport.setRemark(remark);
        outport.setProviderid(ywImport.getProviderid());
        getBaseMapper().insert(outport);
    }

}
