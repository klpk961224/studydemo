package com.future.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.wms.model.entity.SysUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * @Description [保存用户和角色的关系]
     * @Author evanliu
     * @Date 2021-03-25 18:05
     * @param: uid -> 用户的ID
     * @param: ids -> 用户拥有的角色的ID的数组
     * @return void
     **/
    void saveUserRole(Integer uid, Integer[] ids);

    /**
     * @Description [查询当前用户是否是其他用户的直属领导]
     * @Author evanliu
     * @Date 2021-03-25 18:05
     * @param: userId ->
     * @return java.lang.Boolean
     **/
    Boolean queryMgrByUserId(Integer userId);

}
