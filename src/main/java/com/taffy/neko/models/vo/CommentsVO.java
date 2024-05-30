package com.taffy.neko.models.vo;


import lombok.Data;

/**
 * 评论展示类
 */
@Data
public class CommentsVO {
    //评论id
    private String id;

    //正文
    private String content;

    //评论时间
    private String createTime;

    //评论人id
    private String createBy;

    //评论人
    private String userName;
}
