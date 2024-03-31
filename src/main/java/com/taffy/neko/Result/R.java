package com.taffy.neko.Result;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.taffy.neko.enums.ResponseEnum;
import lombok.Data;


//统一返回类
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class R<T> {
    private Integer code;

    private String msg;

    private T data;

    //无参
    public R<T> success(ResponseEnum responseEnum) {
        R<T> result = new R<>();
        result.setMsg(responseEnum.getMessage());
        result.setCode(responseEnum.getCode());
        return result;
    }

    //有参
    public R<T> success(ResponseEnum responseEnum, T data) {
        R<T> result = new R<>();
        result.setMsg(responseEnum.getMessage());
        result.setCode(responseEnum.getCode());
        result.setData(data);
        return result;
    }

    //自定义
    public R<T> error(int code, String msg) {
        R<T> result = new R<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    //失败
    public R<T> error(ResponseEnum responseEnum) {
        R<T> result = new R<>();
        result.setMsg(responseEnum.getMessage());
        result.setCode(responseEnum.getCode());
        return result;
    }
}
