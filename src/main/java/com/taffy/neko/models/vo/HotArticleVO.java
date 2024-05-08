package com.taffy.neko.models.vo;


import lombok.Data;

/**
 * 热门帖子
 */

@Data
public class HotArticleVO {

    private String id;

    private String header;

    //浏览量
    private int views;

    private String coverUrl;
}
