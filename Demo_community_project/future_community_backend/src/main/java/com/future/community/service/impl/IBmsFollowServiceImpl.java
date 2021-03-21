package com.future.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.community.mapper.BmsFollowMapper;
import com.future.community.model.entity.BmsFollow;
import com.future.community.service.IBmsFollowService;
import org.springframework.stereotype.Service;


@Service
public class IBmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements IBmsFollowService {
}
