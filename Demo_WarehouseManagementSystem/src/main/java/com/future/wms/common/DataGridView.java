package com.future.wms.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * json数据实体
 * @author evanliu
 * @create 2021-03-25 17:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {

    private Integer code = 0;
    private String msg = "";
    private Map<String, Object> map = new HashMap<>(16);
    /**
     * 返回的记录总条数
     */
    private Long count = 0L;
    /**
     * 返回的记录
     */
    private Object data;

    public DataGridView(Long count, Object data) {
        this.count = count;
        this.data = data;
    }

    public DataGridView(Object data) {
        this.data = data;
    }

    public DataGridView(String title, Object data) {
        this.map.put(title, data);
    }
}
