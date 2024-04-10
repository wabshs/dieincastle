package com.taffy.neko.models.vo;

import lombok.Data;

@Data
public class ArticleVO {

    private String content;

    private String coverUrl;

    private String userId;

    //浏览量
    private int views;

    //是否置顶
    private int isTop;

    //是否可见
    private int isVisible;

    //作者头像
}
