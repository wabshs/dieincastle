package com.taffy.neko.models.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;


@Data
public class UserRegisterDTO {

    private String id;

    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "只能由数字字母组成用户名")
    private String userName;

    private String password;

    private String nickName;

    private String email;
    
    //邮箱验证码
    private String authCode;
}
