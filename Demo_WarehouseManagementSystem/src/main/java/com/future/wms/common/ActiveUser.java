package com.future.wms.common;

import com.future.wms.model.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description [功能详细描述] 
 * @Author evanliu 
 * @Date 2021-03-24 17:00
 * @param: null -> 
 * @return 
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUser {
    private SysUser sysUser;

    private List<String> roles;

    private List<String> permissions;
}
