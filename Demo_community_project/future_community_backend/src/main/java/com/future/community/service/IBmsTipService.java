package com.future.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.community.model.entity.BmsTip;

public interface IBmsTipService extends IService<BmsTip> {
    BmsTip getRandomTip();
}
