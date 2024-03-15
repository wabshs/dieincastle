package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/aboutMe/{userId}")
    public R<?> aboutMe(@PathVariable String userId){

    }
}
