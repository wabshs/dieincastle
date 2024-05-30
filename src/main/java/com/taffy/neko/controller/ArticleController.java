package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.entity.BlogCollection;
import com.taffy.neko.models.dto.CreateArticleDTO;
import com.taffy.neko.models.dto.DeleteCollectArticleDTO;
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
    public R<?> selectArticleVOByPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam(defaultValue = "") String header) {
        return articleService.selectArticleVOByPage(pageNum, pageSize,header);
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

    @GetMapping("/getHotArticles")
    @ApiOperation(value = "获取五个最热门的帖子")
    public R<?> getHotArticles() {
        return articleService.getHotArticles();
    }

    @GetMapping("/articleCollection")
    @ApiOperation("根据id分页查询用户收藏的帖子")
    public R<?> articleCollection(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String userId) {
        return articleService.articleCollection(pageNum, pageSize, userId);
    }

    @PostMapping("/collectArticle")
    @ApiOperation(value = "收藏帖子")
    public R<?> collectArticle(@RequestBody BlogCollection reqDTO) {
        return articleService.collectArticle(reqDTO);
    }

    @GetMapping("/checkCollect")
    @ApiOperation(value = "检查是否收藏帖子")
    public R<?> collectArticle(@RequestParam String userId, @RequestParam String articleId) {
        return articleService.checkCollect(userId, articleId);
    }

    @PostMapping("/deleteCollectArticle")
    @ApiOperation(value = "取消收藏帖子")
    public R<?> deleteCollectArticle(@RequestBody DeleteCollectArticleDTO reqDTO) {
        return articleService.deleteCollectArticle(reqDTO);
    }

    @GetMapping("/getArticleCollectNums/{articleId}")
    @ApiOperation(value = "查询某个帖子收藏量")
    public R<?> getArticleCollectNums(@PathVariable String articleId) {
        return articleService.getArticleCollectNums(articleId);
    }


    @GetMapping("/getArticleByTagsPage")
    @ApiOperation(value = "根据标签分页查询帖子")
    public R<?> getArticleByTagsPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam("tags") String tags) {
        return articleService.getArticleByTagsPage(pageNum, pageSize, tags);
    }


    @GetMapping("/getTagsById")
    public R<?> getTagsById(@RequestParam String id) {
        return articleService.getTagsById(id);
    }


}
