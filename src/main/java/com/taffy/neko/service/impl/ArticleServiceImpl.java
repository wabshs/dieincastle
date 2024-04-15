package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Article;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.ArticleMapper;
import com.taffy.neko.models.convertor.ArticleConvert;
import com.taffy.neko.models.dto.CreateArticleDTO;
import com.taffy.neko.models.vo.ArticleDetailVO;
import com.taffy.neko.models.vo.ArticleVO;
import com.taffy.neko.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

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


}
