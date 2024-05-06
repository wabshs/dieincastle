package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.models.dto.CreateArticleDTO;
import com.taffy.neko.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
    public R<?> getArticleById(@PathVariable String id) {
        return articleService.getArticleById(id);
    }

    @PostMapping("/createArticle")
    @ApiOperation(value = "新建帖子")
    public R<?> createArticle(@RequestBody CreateArticleDTO reqDTO) {
        return articleService.createArticle(reqDTO);
    }

    @GetMapping("/selectArticlePage")
    @ApiOperation(value = "查找帖子")
    public R<?> selectArticleVOByPage(@RequestParam int pageNum, @RequestParam int pageSize) {
        return articleService.selectArticleVOByPage(pageNum, pageSize);
    }

    @PutMapping("/updateViews/{id}")
    @ApiOperation(value = "更新浏览量")
    public R<?> updateViews(@PathVariable String id) {
        return articleService.updateArticleViews(id);
    }

    @GetMapping("/getArticleTags")
    @ApiOperation(value = "获取文章标签")
    public R<?> getArticleTags() {
        return articleService.getArticleTags();
    }

    @GetMapping("/getArticleCount")
    @ApiOperation(value = "获取文章数量")
    public R<?> getArticleCount() {
        return articleService.getArticleCount();
    }

}
