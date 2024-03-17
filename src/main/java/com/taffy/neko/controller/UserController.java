package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
public class UserController {


    @Resource
    private UserService userService;

    @ApiImplicitParam(name = "id", value = "id", required = true)
    @ApiOperation(value = "根据id获取关于我的信息")
    @GetMapping("/aboutMe/{id}")
    public R<?> aboutMe(@PathVariable String id) {
        return userService.getUserAboutMe(id);
    }
}
