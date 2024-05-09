package com.taffy.neko.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("chat_list")
public class ChatList {

    private String toId;

    private String fromId;

    private String toNickName;

    private String fromNickName;

    private String toAvatarUrl;

    private String fromAvatarUrl;

    //最后操作的时间
    private String lastTime;
}
