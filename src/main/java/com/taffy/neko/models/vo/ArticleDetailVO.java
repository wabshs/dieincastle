package com.taffy.neko.models.vo;


import lombok.Data;

@Data
public class ArticleDetailVO {
    private String id;

    private String content;

    //标题
    private String header;

    //作者id
    private String userId;

    //浏览量
    private int views;

    //创建时间
    private String createTime;
}
