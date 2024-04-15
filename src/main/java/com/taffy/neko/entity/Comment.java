package com.taffy.neko.entity;


import lombok.Data;

/**
 * @author 杜昱恒
 * 评论表
 */
@Data
public class Comment {
    private String id;

    private String content;

    private String userId;

    private String time;

    private String pid;

    private String originId;

    private String articleId;
}
