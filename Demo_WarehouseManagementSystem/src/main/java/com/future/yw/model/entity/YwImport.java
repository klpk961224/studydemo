package com.future.yw.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@ToString
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

    /**
     * 供应商姓名
     */
    @TableField(exist = false)
    private String providername;

    /**
     * 商品名称
     */
    @TableField(exist = false)
    private String goodsname;

    /**
     * 商品规格
     */
    @TableField(exist = false)
    private String size;


}
