package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Article;

public interface ArticleService extends IService<Article> {


    /**
     *
     * @param id 文章id
     * @return 文章详细信息
     */
    R<?> getArticleById(int id);
}
