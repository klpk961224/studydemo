package com.future.wms.service.impl;

import com.future.wms.model.entity.SysNotice;
import com.future.wms.mapper.SysNoticeMapper;
import com.future.wms.service.ISysNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@Service
@Transactional
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements ISysNoticeService {

}
