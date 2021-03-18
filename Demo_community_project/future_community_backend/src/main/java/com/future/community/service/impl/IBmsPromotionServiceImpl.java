package com.future.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.community.mapper.BmsPromotionMapper;
import com.future.community.model.entity.BmsPromotion;
import com.future.community.service.IBmsPromotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IBmsPromotionServiceImpl extends ServiceImpl<BmsPromotionMapper, BmsPromotion> implements IBmsPromotionService {
}
