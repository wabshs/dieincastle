package com.taffy.neko.models.vo;

import lombok.Data;

@Data
public class ArticleVO {

    private String content;

    private String cover_url;

    private String user_id;

    //浏览量
    private int views;

    //是否置顶
    private int isTop;

    //是否可见
    private int isVisible;
}
