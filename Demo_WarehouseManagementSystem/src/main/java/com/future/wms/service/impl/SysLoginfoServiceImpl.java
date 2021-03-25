package com.future.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.wms.mapper.SysLoginfoMapper;
import com.future.wms.model.entity.SysLoginfo;
import com.future.wms.service.ISysLoginfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author evanliu
 * @create 2021-03-24 23:27
 */
@Service
@Transactional
public class SysLoginfoServiceImpl extends ServiceImpl<SysLoginfoMapper, SysLoginfo> implements ISysLoginfoService {

}
