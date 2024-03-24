package com.taffy.neko.enums;


import lombok.Getter;

@Getter
public enum ResponseEnum {

    SUCCESS(200, "操作成功"),
    ERROR(500, "服务器异常"),
    NOT_FOUND(404, "资源未找到"),
    UNAUTHORIZED(401, "未授权");

    private final int code;

    private final String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
