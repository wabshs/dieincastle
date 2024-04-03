package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.models.dto.UpdateAboutMeDTO;
import com.taffy.neko.models.dto.UserLoginDTO;
import com.taffy.neko.models.dto.UserRegisterDTO;
import com.taffy.neko.service.EmailService;
import com.taffy.neko.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation(value = "修改 关于我 的内容")
    @PutMapping("/updateAboutMe")
    public R<?> updateAboutMe(@RequestBody UpdateAboutMeDTO reqDTO) {
        return userService.updateAboutMe(reqDTO);
    }

    @GetMapping("/userProfile/{id}")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    @ApiOperation(value = "根据id获取个人资料")
    public R<?> getUserProfile(@PathVariable String id) {
        return userService.getUserProfile(id);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/userRegister")
    public R<?> userRegister(@Validated @RequestBody UserRegisterDTO reqDTO) {
        return userService.userRegister(reqDTO);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/userLogin")
    public R<?> userLogin(@Validated @RequestBody UserLoginDTO reqDTO) {
        return userService.userLogin(reqDTO);
    }



}
