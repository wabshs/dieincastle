package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.taffy.neko.entity.ArticleTags;
import com.taffy.neko.mapper.ArticleTagsMapper;
import com.taffy.neko.service.ArticleTagsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ArticleTagsServiceImpl extends ServiceImpl<ArticleTagsMapper, ArticleTags> implements ArticleTagsService {

    @Resource
    private ArticleTagsMapper articleTagsMapper;


    @Override
    public List<ArticleTags> selectTagsBatchByIds(List<Integer> ids) {

        return articleTagsMapper.selectBatchIds(ids);

    }
}
