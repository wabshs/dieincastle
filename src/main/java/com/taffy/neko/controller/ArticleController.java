package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/article")
@Api(tags = "文章模块")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/{id}")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    @ApiOperation(value = "根据id获取关于文章信息")
    public R<?> getArticleById(@PathVariable int id) {
        return articleService.getArticleById(id);
    }
}
