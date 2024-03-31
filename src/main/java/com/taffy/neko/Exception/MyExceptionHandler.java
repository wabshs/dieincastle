package com.taffy.neko.Exception;


import com.taffy.neko.Result.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public R<?> handle(ServiceException e) {
        return new R<>().error(e.getCode(), e.getMessage());
    }
}
