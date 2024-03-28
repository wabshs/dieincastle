package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Article;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.ArticleMapper;
import com.taffy.neko.models.convertor.ArticleConvert;
import com.taffy.neko.models.dto.CreateArticleDTO;
import com.taffy.neko.models.vo.ArticleVO;
import com.taffy.neko.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public R<?> getArticleById(String id) {
        Article article = articleMapper.selectById(id);
        ArticleVO articleVO = ArticleConvert.INSTANCE.toArticleVO(article);
        return new R<>().success(ResponseEnum.SUCCESS, articleVO);
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
}
