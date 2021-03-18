package com.future.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.community.mapper.BmsBillboardMapper;
import com.future.community.model.entity.BmsBillboard;
import com.future.community.service.IBmsBillboardService;
import org.springframework.stereotype.Service;

@Service
public class IBmsBillboardServiceImpl extends ServiceImpl<BmsBillboardMapper, BmsBillboard> implements IBmsBillboardService {
}
