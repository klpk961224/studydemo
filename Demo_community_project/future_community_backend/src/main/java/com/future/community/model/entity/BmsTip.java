package com.future.community.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Accessors(chain = true)
@TableName("bms_tip")
@NoArgsConstructor
@AllArgsConstructor
public class BmsTip implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名句内容
     */
    @TableField("content")
    private String content;

    /**
     * 名句作者
     */
    @TableField("author")
    private String author;


    /**
     * 1:使用, 0:过期
     */
    @Builder.Default
    @TableField("`type`")
    private boolean type = false;
}
