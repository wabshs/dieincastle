package com.taffy.neko.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taffy.neko.entity.Comment;
import com.taffy.neko.models.vo.CommentDetailVO;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentDetailVO> findCommentDetail(String articleId);
}
