package com.taffy.neko.controller;


import com.taffy.neko.Exception.ServiceException;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Comment;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.service.CommentService;
import com.taffy.neko.utils.LangChainSDK;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论模块")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private LangChainSDK langChainSDK;


    @GetMapping("/commentList")
    @ApiOperation(value = "获取评论（带子评论）")
    public R<?> commentList(@RequestParam String articleId, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return commentService.commentList(articleId, pageNum, pageSize);
    }

    @PostMapping
    public R<?> addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @PostMapping("/test")
    public R<?> test(@RequestParam String content) {
        String s = langChainSDK.chatWithAI(content);
        return new R<>().success(ResponseEnum.SUCCESS, s);
    }
}
