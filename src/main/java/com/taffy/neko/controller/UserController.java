package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;

    @GetMapping("/aboutMe/{id}")
    public R<?> aboutMe(@PathVariable String id) {
        return userService.getUserAboutMe(id);
    }
}
