package com.taffy.neko.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("article_tags")
public class ArticleTags {

    private Integer id;

    private String name;

    private String color;
}
