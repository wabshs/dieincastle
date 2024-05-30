package com.taffy.neko.models.vo;



import lombok.Data;



@Data
public class UserInfoVO {

    private String id;

    private String nickName;

    private String userName;

    private int isDeleted;

    private String email;

}
