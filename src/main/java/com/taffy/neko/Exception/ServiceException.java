package com.taffy.neko.Exception;


import com.taffy.neko.enums.ResponseEnum;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private int code;

    private String msg;

    public ServiceException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMessage();
    }
}
