package com.taffy.neko.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("article")
public class Article {

    @TableId
    private int id;

    private String content;

    private String cover_url;

    private String user_id;

    //浏览量
    private int views;

    //是否置顶
    private int isTop;

    //是否可见
    private int isVisible;

    //标题
    private String header;
}
