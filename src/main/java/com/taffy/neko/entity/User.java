package com.taffy.neko.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("user")
public class User {

    @TableId
    private String id;

    private String nickName;

    private String userName;

    private String password;

    private String sign;

    private String aboutMe;

    private String likes;

    private int isDeleted;
}
