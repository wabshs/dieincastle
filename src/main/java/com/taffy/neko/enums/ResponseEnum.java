package com.taffy.neko.enums;


import lombok.Getter;

@Getter
public enum ResponseEnum {

    USERNAME_ALREADY_EXIST(500, "用户名已存在"),
    LOGIN_ERROR(500, "用户名或密码错误"),
    SUCCESS(200, "操作成功"),
    ERROR(500, "服务器异常"),
    NOT_FOUND(404, "资源未找到"),
    AUTH_CODE_ERROR(500, "验证码错误"),
    LANG_CHAIN_CHAT_ERROR(421, "LangChain出错"),
    CHAT_LIST_EMPTY(201, "无历史对话记录"),
    TOKEN_ERROR(401, "Token验证失败，请重新登陆!"),
    USER_NULL(401, "用户不存在!"),
    TOKEN_NULL_UNAUTHORIZED(401, "无Token，请重新登录!");


    private final int code;

    private final String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
