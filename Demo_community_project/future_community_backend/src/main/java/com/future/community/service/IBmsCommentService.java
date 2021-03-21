package com.future.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.community.model.dto.CommentDTO;
import com.future.community.model.entity.BmsComment;
import com.future.community.model.entity.UmsUser;
import com.future.community.model.vo.CommentVO;

import java.util.List;


public interface IBmsCommentService extends IService<BmsComment> {
    /**
     *
     *
     * @param topicid
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    BmsComment create(CommentDTO dto, UmsUser principal);
}
