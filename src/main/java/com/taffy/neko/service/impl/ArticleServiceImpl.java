package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.mail.iap.Response;
import com.taffy.neko.Exception.ServiceException;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Article;
import com.taffy.neko.entity.ArticleTags;
import com.taffy.neko.entity.BlogCollection;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.ArticleMapper;
import com.taffy.neko.mapper.BlogCollectionMapper;
import com.taffy.neko.models.convertor.ArticleConvert;
import com.taffy.neko.models.dto.CreateArticleDTO;
import com.taffy.neko.models.dto.DeleteCollectArticleDTO;
import com.taffy.neko.models.vo.ArticleDetailVO;
import com.taffy.neko.models.vo.ArticleVO;
import com.taffy.neko.models.vo.HotArticleVO;
import com.taffy.neko.models.vo.PageVo;
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

    @Resource
    private BlogCollectionMapper blogCollectionMapper;

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

    @Override
    public R<?> getHotArticles() {
        LambdaUpdateWrapper<Article> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.orderByDesc(Article::getViews)
                .last("limit 5");
        List<Article> articles = articleMapper.selectList(lambdaUpdateWrapper);
        List<HotArticleVO> hotArticleVOList = ArticleConvert.INSTANCE.toHotArticleVOList(articles);
        return new R<>().success(ResponseEnum.SUCCESS, hotArticleVOList);
    }

    @Override
    public R<?> articleCollection(int pageNum, int pageSize, String userId) {
        LambdaQueryWrapper<BlogCollection> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(BlogCollection::getUserId, userId)
                .orderByDesc(BlogCollection::getCreateTime);
        Page<BlogCollection> page = blogCollectionMapper.selectPage(new Page<>(pageNum, pageSize), lambdaQueryWrapper);
        //封装成PageVO
        PageVo articleCollection = new PageVo(page.getRecords(), page.getTotal());
        return new R<>().success(ResponseEnum.SUCCESS, articleCollection);
    }

    @Override
    public R<?> collectArticle(BlogCollection reqDTO) {
        int insert = blogCollectionMapper.insert(reqDTO);
        if (insert == 1) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            throw new ServiceException(ResponseEnum.ERROR);
        }

    }

    @Override
    public R<?> checkCollect(String userId, String articleId) {
        LambdaQueryWrapper<BlogCollection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogCollection::getArticleId, articleId)
                .eq(BlogCollection::getUserId, userId);
        BlogCollection blogCollection = blogCollectionMapper.selectOne(queryWrapper);
        //查到了
        if (blogCollection != null) {
            return new R<>().success(ResponseEnum.ARTICLE_COLLECTED);
        } else {
            return new R<>().error(ResponseEnum.ARTICLE_UNCOLLECTED);
        }

    }

    @Override
    public R<?> deleteCollectArticle(DeleteCollectArticleDTO reqDTO) {
        LambdaQueryWrapper<BlogCollection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogCollection::getArticleId, reqDTO.getArticleId())
                .eq(BlogCollection::getUserId, reqDTO.getUserId());
        int delete = blogCollectionMapper.delete(queryWrapper);
        if (delete == 1) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            throw new ServiceException(ResponseEnum.ERROR);
        }
    }

    @Override
    public R<?> getArticleCollectNums(String articleId) {
        LambdaQueryWrapper<BlogCollection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogCollection::getArticleId, articleId);
        Long l = blogCollectionMapper.selectCount(queryWrapper);
        return new R<>().success(ResponseEnum.SUCCESS, l);
    }
}
