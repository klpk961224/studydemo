package com.future.wms.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusSalesback implements Serializable {

    private static final long serialVersionUID = 7878887750684607744L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer customerid;

    private String paytype;

    private Date salesbacktime;

    private Double salebackprice;

    private String operateperson;

    private Integer number;

    private String remark;

    private Integer goodsid;


}
