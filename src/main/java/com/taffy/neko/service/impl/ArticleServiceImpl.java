package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Article;
import com.taffy.neko.entity.ArticleTags;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.ArticleMapper;
import com.taffy.neko.models.convertor.ArticleConvert;
import com.taffy.neko.models.dto.CreateArticleDTO;
import com.taffy.neko.models.vo.ArticleDetailVO;
import com.taffy.neko.models.vo.ArticleVO;
import com.taffy.neko.service.ArticleService;
import com.taffy.neko.service.ArticleTagsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleTagsService articleTagsService;

    @Override
    public R<?> getArticleById(String id) {
        Article article = articleMapper.selectById(id);
        ArticleDetailVO articleDetailVO = ArticleConvert.INSTANCE.toArticleDetailVO(article);
        return new R<>().success(ResponseEnum.SUCCESS, articleDetailVO);
    }

    @Override
    public R<?> createArticle(CreateArticleDTO reqDTO) {
        Article article = ArticleConvert.INSTANCE.toArticleDO(reqDTO);
        int ifInsert = articleMapper.insert(article);
        if (ifInsert == 1) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            return new R<>().error(ResponseEnum.ERROR);
        }
    }

    @Override
    public R<?> selectArticleVOByPage(int pageNum, int pageSize) {
        Page<ArticleVO> page = new Page<>(pageNum, pageSize);
        List<ArticleVO> articleVOList = articleMapper.selectArticleVOByPage(page);
        articleVOList.forEach(articleVO -> {
            List<ArticleTags> tagsList = selectArticleTags(articleVO.getId());
            articleVO.setArticleTags(tagsList);
        });
        return new R<>().success(ResponseEnum.SUCCESS, articleVOList);
    }

    @Override
    public R<?> updateArticleViews(String id) {
        boolean ifUpdate = articleMapper.updateViews(id);
        if (ifUpdate) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            return new R<>().error(ResponseEnum.ERROR);
        }
    }

    @Override
    public List<ArticleTags> selectArticleTags(String articleId) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getId, articleId);
        Article article = articleMapper.selectOne(lambdaQueryWrapper);
        String tags = article.getTags();//标签
        List<Integer> tagIds = Arrays
                .stream(tags.split(","))
                .map(s -> Integer.parseInt(s.trim()))
                .collect(Collectors.toList()); // ---------把String转化成List---------
        return articleTagsService.selectTagsBatchByIds(tagIds);
    }

    @Override
    public R<?> getArticleTags() {
        List<ArticleTags> articleTagsList = articleTagsService.list();
        return new R<>().success(ResponseEnum.SUCCESS, articleTagsList);
    }

    @Override
    public R<?> getArticleCount() {
        long count = count();
        return new R<>().success(ResponseEnum.SUCCESS, count);
    }
}
