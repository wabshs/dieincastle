package com.taffy.neko.models.vo;


import lombok.Data;

//私信栏左边的部分 头像+名字
@Data
public class ChatLeftVO {

    private String userNickName;

    private String userAvatarUrl;

    private String userId;
}
