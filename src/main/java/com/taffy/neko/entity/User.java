package com.taffy.neko.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

@Data
@TableName("user")
@Validated
public class User {

    //雪花算法自动生成id  String类型不会丢失精度
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String nickName;

    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "只能由数字字母组成用户名")
    private String userName;

    private String password;

    private String sign;

    private String aboutMe;

    private String likes;

    private String avatarUrl;

    private int isDeleted;

    private String email;
}
