package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.service.EmailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Resource
    private EmailService emailService;

    @PostMapping("/sendAuthCode")
    public R<?> sendAuthCode(@RequestParam String to){
        return emailService.sendAuhCodeByEmail(to);
    }
}
