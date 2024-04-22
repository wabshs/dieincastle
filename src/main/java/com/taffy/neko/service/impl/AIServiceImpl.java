package com.taffy.neko.service.impl;

import com.taffy.neko.service.AIService;
import com.taffy.neko.utils.LangChainSDK;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AIServiceImpl implements AIService {

    @Resource
    private LangChainSDK langChainSDK;


    @Override
    public String chatWithAI(String content) {
        return langChainSDK.chatWithAI(content);
    }
}
