package com.future.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.community.mapper.BmsTipMapper;
import com.future.community.model.entity.BmsTip;
import com.future.community.service.IBmsTipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IBmsTipServiceImpl extends ServiceImpl<BmsTipMapper, BmsTip> implements IBmsTipService {
    @Override
    public BmsTip getRandomTip() {
        BmsTip todayTip = null;
        try {
            todayTip = this.baseMapper.getRandomTip();
        }catch (Exception e){
            log.info("tip转化失败");
        }
        return todayTip;
    }
}
