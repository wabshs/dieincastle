package com.taffy.neko.Exception;


import com.taffy.neko.Result.R;
import com.taffy.neko.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public R<?> handle(ServiceException e) {
        log.error("出现异常! :{}", e.getMsg());
        return new R<>().error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R<?> ExceptionHandle(Exception e) {
        log.error("出现了异常! :{}", e.getMessage());
        return new R<>().error(ResponseEnum.ERROR.getCode(), e.getMessage());
    }
}
