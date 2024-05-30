package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Article;
import com.taffy.neko.entity.ArticleTags;
import com.taffy.neko.entity.BlogCollection;
import com.taffy.neko.models.dto.CreateArticleDTO;
import com.taffy.neko.models.dto.DeleteCollectArticleDTO;

import java.util.List;

public interface ArticleService extends IService<Article> {


    /**
     * @param id 文章id
     * @return 文章详细信息
     */
    R<?> getArticleById(String id);

    R<?> createArticle(CreateArticleDTO reqDTO);

    R<?> selectArticleVOByPage(int pageNum, int pageSize,String header);

    //更新文章浏览量
    R<?> updateArticleViews(String id);

    List<ArticleTags> selectArticleTags(String articleId);

    R<?> getArticleTags();

    R<?> getArticleCount();

    R<?> getHotArticles();

    R<?> articleCollection(int pageNum, int pageSize, String userId);

    R<?> collectArticle(BlogCollection reqDTO);

    R<?> checkCollect(String userId, String articleId);

    R<?> deleteCollectArticle(DeleteCollectArticleDTO reqDTO);

    R<?> getArticleCollectNums(String articleId);

    R<?> getArticleByTagsPage(int pageNum, int pageSize, String tags);

    R<?> getTagsById(String id);
}
