package com.taffy.neko.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@TableName("article")
public class Article {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @NotBlank(message = "正文不能为空")
    private String content;

    @NotBlank(message = "文章封面不能为空")
    private String coverUrl;

    private String userId;

    //浏览量
    private int views;

    //是否置顶
    private int isTop;

    //是否可见
    private int isVisible;

    //标题
    @NotBlank(message = "标题不能为空")
    private String header;

    private String createTime;

    //帖子标签
    private String tags;
}
