package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taffy.neko.Exception.ServiceException;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Article;
import com.taffy.neko.entity.Comment;
import com.taffy.neko.entity.User;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.ArticleMapper;
import com.taffy.neko.mapper.CommentMapper;
import com.taffy.neko.mapper.UserMapper;
import com.taffy.neko.models.convertor.CommentConvert;
import com.taffy.neko.models.convertor.UserConvert;
import com.taffy.neko.models.dto.UpdateUserStatusDTO;
import com.taffy.neko.models.vo.CommentsVO;
import com.taffy.neko.models.vo.PageVo;
import com.taffy.neko.models.vo.UserInfoVO;
import com.taffy.neko.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserMapper userMapper;


    @Override
    public R<?> articlePage(int pageNum, int pageSize, String header, String sortBy) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Article::getHeader, header);
        //0按创建时间排序1按浏览量排序
        if (Objects.equals(sortBy, "0")) {
            lambdaQueryWrapper.orderByDesc(Article::getCreateTime);
        } else if (Objects.equals(sortBy, "1")) {
            lambdaQueryWrapper.orderByDesc(Article::getViews);
        }
        //总数
        Long total = articleMapper.selectCount(lambdaQueryWrapper);
        Page<Article> rowsPage = articleMapper.selectPage(page, lambdaQueryWrapper);
        PageVo pageVo = new PageVo(rowsPage.getRecords(), total);
        return new R<>().success(ResponseEnum.SUCCESS, pageVo);
    }

    @Override
    public R<?> deleteArticleById(String id) {
        int i = articleMapper.deleteById(id);
        if (i == 1) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            throw new ServiceException(ResponseEnum.ERROR);
        }
    }

    @Override
    public R<?> getCommentsById(int pageNum, int pageSize, String id) {
        Page<Comment> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getArticleId, id);
        //把此文章所有评论查询出来
        Long total = commentMapper.selectCount(lambdaQueryWrapper);
        List<Comment> comments = commentMapper.selectList(page, lambdaQueryWrapper);
        //转化为展示类
        List<CommentsVO> commentsVO = CommentConvert.INSTANCE.toCommentsVO(comments);
        //遍历每个展示类然后把它们的userName全部赋值
        commentsVO = commentsVO.stream().peek(commentVO -> {
            String userName = userMapper.selectById(commentVO.getCreateBy()).getUserName();
            commentVO.setUserName(userName);
        }).collect(Collectors.toList());
        PageVo pageVo = new PageVo(commentsVO, total);
        return new R<>().success(ResponseEnum.SUCCESS, pageVo);
    }

    @Override
    public R<?> deleteCommentById(String id) {
        int i = commentMapper.deleteById(id);
        if (i == 1) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            throw new ServiceException(ResponseEnum.ERROR);
        }
    }

    @Override
    public R<?> userInfoPage(int pageNum, int pageSize, String userName, String nickName) {
        Page<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getUserName, userName)
                .like(User::getNickName, nickName);
        Long total = userMapper.selectCount(lambdaQueryWrapper);
        List<User> users = userMapper.selectList(page, lambdaQueryWrapper);
        List<UserInfoVO> userInfoVOList = UserConvert.INSTANT.toUserInfoVOList(users);//转化
        PageVo pageVo = new PageVo(userInfoVOList, total);
        return new R<>().success(ResponseEnum.SUCCESS, pageVo);
    }

    @Override
    public R<?> updateStatus(UpdateUserStatusDTO reqDTO) {
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(User::getId, reqDTO.getId());
        User user = new User();
        user.setIsDeleted(reqDTO.getIsDeleted());
        int update = userMapper.update(user, lambdaUpdateWrapper);
        if (update == 1) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            throw new ServiceException(ResponseEnum.ERROR);
        }
    }
}
