package com.taffy.neko.models.dto;


import lombok.Data;

@Data
public class CreateArticleDTO {

    //发文章人的id
    private String userId;

    //正文内容
    private String content;

    //封面图案
    private String coverUrl;

    //标题
    private String header;
}
