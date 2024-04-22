package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.taffy.neko.Exception.ServiceException;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Comment;
import com.taffy.neko.entity.User;
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
                .eq(Comment::getRootId, "-1");

        //分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        //DO -> VO
        List<CommentVO> commentVOList = toCommentVOList(page.getRecords());

        //查询所有根评论对应的子评论集合，并复制给相应的熟悉
        for (CommentVO commentVO : commentVOList) {
            //查询对应的子评论
            List<CommentVO> children = getChildren(commentVO.getId());
            //查头像
            User user = userMapper.selectById(commentVO.getCreateBy());
            commentVO.setAvatarUrl(user.getAvatarUrl());
            //赋值
            commentVO.setChildren(children);
        }
        //返回
        return new R<>().success(ResponseEnum.SUCCESS, new PageVo(commentVOList, page.getTotal()));
    }

    @Override
    public R<?> addComment(Comment comment) {
        boolean isSave = save(comment);
        if (isSave) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            throw new ServiceException(ResponseEnum.ERROR);
        }

    }

    /**
     * 根据根评论Id返回子评论的集合
     *
     * @param id 根评论Id
     * @return 对应的子评论集合
     */
    private List<CommentVO> getChildren(String id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //根据创建时间排序
        queryWrapper.eq(Comment::getRootId, id)
                .orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        return toCommentVOList(comments);
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
