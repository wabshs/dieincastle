package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.entity.ArticleTags;

import java.util.List;

public interface ArticleTagsService extends IService<ArticleTags> {

    List<ArticleTags> selectTagsBatchByIds(List<Integer> ids);
}
