package com.taffy.neko.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("chat")
public class Chat {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String fromId;

    private String toId;

    private String content;

    private Integer isRead;

    private String createTime;
}
