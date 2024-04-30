package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Article;
import com.taffy.neko.entity.ArticleTags;
import com.taffy.neko.models.dto.CreateArticleDTO;

import java.util.List;

public interface ArticleService extends IService<Article> {


    /**
     * @param id 文章id
     * @return 文章详细信息
     */
    R<?> getArticleById(String id);

    R<?> createArticle(CreateArticleDTO reqDTO);

    R<?> selectArticleVOByPage(int pageNum, int pageSize);

    //更新文章浏览量
    R<?> updateArticleViews(String id);

    List<ArticleTags> selectArticleTags(String articleId);
}
