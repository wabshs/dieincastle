package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.mapper.CommentMapper;
import com.taffy.neko.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/getCommentDetail/{articleId}")
    public R<?> findCommentDetail(@PathVariable String articleId) {
        return commentService.findCommentDetail(articleId);
    }
}
