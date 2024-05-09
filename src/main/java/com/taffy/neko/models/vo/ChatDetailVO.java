package com.taffy.neko.models.vo;


import com.taffy.neko.entity.Chat;
import lombok.Data;

@Data
public class ChatDetailVO {

    private String id;

    private String fromId;

    private String toId;

    private String content;

    private Integer isRead;

    private String createTime;

    private String fromAvatarUrl;

    private String toAvatarUrl;

}
