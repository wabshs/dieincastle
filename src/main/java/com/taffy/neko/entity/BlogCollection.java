package com.taffy.neko.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 文章收藏类
 */
@Data
@TableName("blog_collection")
public class BlogCollection {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String articleId;

    private String userId;

    private String createTime;

    private String articleHeader;
}
