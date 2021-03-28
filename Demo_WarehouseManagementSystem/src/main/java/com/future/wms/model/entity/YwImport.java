package com.future.wms.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("yw_import")
public class YwImport implements Serializable {

    private static final long serialVersionUID = -3097205265481812825L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String paytype;

    private Date importtime;

    private String operateperson;

    private Integer number;

    private String remark;

    private Double importprice;

    private Integer providerid;

    private Integer goodsid;


}
