package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Comment;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.CommentMapper;
import com.taffy.neko.models.vo.CommentDetailVO;
import com.taffy.neko.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public R<?> findCommentDetail(String articleId) {
        List<CommentDetailVO> commentDetailVOList = commentMapper.findCommentDetail(articleId);
        return new R<>().success(ResponseEnum.SUCCESS, commentDetailVOList);
    }
}
