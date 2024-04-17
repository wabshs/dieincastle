package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.taffy.neko.entity.Comment;
import com.taffy.neko.mapper.CommentMapper;
import com.taffy.neko.service.CommentService;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


}
