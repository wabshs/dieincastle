package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Comment;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.CommentMapper;
import com.taffy.neko.mapper.UserMapper;
import com.taffy.neko.models.convertor.CommentConvert;
import com.taffy.neko.models.vo.CommentVO;
import com.taffy.neko.models.vo.PageVo;
import com.taffy.neko.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private UserMapper userMapper;


    @Override
    public R<?> commentList(String articleId, Integer pageNum, Integer pageSize) {


        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对ArticleId进行判断
        queryWrapper.eq(Comment::getArticleId, articleId)
                .eq(Comment::getRootId, -1);

        //分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        //DO -> VO
        List<CommentVO> commentVOList = toCommentVOList(page.getRecords());
        //返回
        return new R<>().success(ResponseEnum.SUCCESS, new PageVo(commentVOList, page.getTotal()));
    }

    private List<CommentVO> toCommentVOList(List<Comment> list) {
        List<CommentVO> commentVOList = CommentConvert.INSTANCE.toCommentVOList(list);
        for (CommentVO commentVO : commentVOList) {
            String nickName = userMapper.selectById(commentVO.getCreateBy()).getNickName();
            commentVO.setNickName(nickName);
            if (!Objects.equals(commentVO.getToCommentId(), "-1")) {
                String toCommentUserName = userMapper.selectById(commentVO.getToCommentUserId()).getNickName();
                commentVO.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVOList;
    }
}
