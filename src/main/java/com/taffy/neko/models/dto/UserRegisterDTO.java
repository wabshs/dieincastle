package com.taffy.neko.models.dto;

import lombok.Data;



@Data
public class UserRegisterDTO {

    private String id;

    private String userName;

    private String password;

    private String nickName;

    private String email;

    private String avatarUrl;
}
