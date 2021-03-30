package com.future.yw.model.vo;

import com.future.yw.model.entity.YwGoods;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author evanliu
 * @create 2021-03-30 18:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class YwGoodsVo extends YwGoods {

    private Integer page = 1;
    private Integer limit = 10;
}
