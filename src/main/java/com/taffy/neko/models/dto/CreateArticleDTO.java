package com.taffy.neko.models.dto;


import lombok.Data;

@Data
public class CreateArticleDTO {

    //发文章人的id
    private String userId;

    //正文内容
    private String content;

    //封面图片链接
    private String coverUrl;

    //标题
    private String header;

    //标签
    private String tags;
}
