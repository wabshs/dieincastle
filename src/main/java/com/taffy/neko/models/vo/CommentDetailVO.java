package com.taffy.neko.models.vo;


import lombok.Data;


//评论详情 包括评论人的昵称和头像url
@Data
public class CommentDetailVO {
    private String id;

    private String content;

    private String userId;

    private String time;

    private String pid;

    private String originId;

    private String articleId;

    private String avatarUrl;

    private String nickName;
}
