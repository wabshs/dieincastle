package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Article;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.ArticleMapper;
import com.taffy.neko.models.convertor.ArticleDOToVOConvert;
import com.taffy.neko.models.vo.ArticleVO;
import com.taffy.neko.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public R<?> getArticleById(int id) {
        Article article = articleMapper.selectById(id);
        ArticleVO articleVO = ArticleDOToVOConvert.INSTANCE.toArticleVO(article);
        return new R<>().success(ResponseEnum.SUCCESS, articleVO);
    }
}
