package com.taffy.neko.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("user")
public class User {

    //雪花算法自动生成id  String类型不会丢失精度
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;

    private String nickName;

    private String userName;

    private String password;

    private String sign;

    private String aboutMe;

    private String likes;

    private String avatarUrl;

    private int isDeleted;

    private String email;
}
