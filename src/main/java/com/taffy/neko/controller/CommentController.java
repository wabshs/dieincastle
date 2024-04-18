package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;


    @GetMapping("/commentList")
    public R<?> commentList(@RequestParam String articleId, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return commentService.commentList(articleId, pageNum, pageSize);
    }
}
