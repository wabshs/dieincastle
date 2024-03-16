package com.taffy.neko.Result;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


//统一返回类
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class R<T> {
    private Integer code;

    private String msg;

    private T data;

    //无参
    public R<T> success(Integer code, String msg) {
        R<T> result = new R<T>();
        result.setMsg("操作成功");
        result.setCode(200);
        return result;
    }

    //有参
    public R<T> success(Integer code, String msg, T data) {
        R<T> result = new R<T>();
        result.setMsg("操作成功");
        result.setCode(200);
        result.setData(data);
        return result;
    }

    //失败
    public R<T> error(Integer code, String msg) {
        R<T> result = new R<T>();
        result.setMsg(msg);
        result.setCode(code);
        return result;
    }
}
