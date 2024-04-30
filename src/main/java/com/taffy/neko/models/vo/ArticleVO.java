package com.taffy.neko.models.vo;

import com.taffy.neko.entity.ArticleTags;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVO {

    private String id;

    //标题
    private String header;

    //封面链接
    private String coverUrl;

    //作者id
    private String userId;

    //浏览量
    private int views;

    //是否置顶
    private int isTop;

    //是否可见
    private int isVisible;

    //作者头像
    private String avatarUrl;

    private List<ArticleTags> articleTags;
}
