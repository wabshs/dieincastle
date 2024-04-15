package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Comment;
import com.taffy.neko.models.vo.CommentDetailVO;

import java.util.List;

public interface CommentService extends IService<Comment> {


    /**
     * 根据文章id查询相关评论
     *
     * @param articleId-文章id
     * @return R
     */
    R<?> findCommentDetail(String articleId);
}
