package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Comment;
import com.taffy.neko.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论模块")
public class CommentController {

    @Resource
    private CommentService commentService;


    @GetMapping("/commentList")
    @ApiOperation(value = "获取评论（带子评论）")
    public R<?> commentList(@RequestParam String articleId, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return commentService.commentList(articleId, pageNum, pageSize);
    }

    @PostMapping
    public R<?> addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}
