package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.service.AIService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ai")
@Api(tags = "AI助手模块")
public class AIController {

    @Resource
    private AIService aiService;

    @PostMapping("/chat")
    @ApiOperation(value = "和本地知识库对话")
    public R<?> chatWithAI(@RequestParam String content) {
        return new R<>().success(ResponseEnum.SUCCESS, aiService.chatWithAI(content));
    }

}
