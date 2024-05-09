package com.taffy.neko.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Exception.ServiceException;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.ChatList;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.ChatListMapper;
import com.taffy.neko.service.ChatListService;
import org.springframework.stereotype.Service;


@Service
public class ChatListServiceImpl extends ServiceImpl<ChatListMapper, ChatList> implements ChatListService {


    @Override
    public R<?> createNewChat(ChatList chatList) {
        boolean save = save(chatList);
        if (save) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            throw new ServiceException(ResponseEnum.ERROR);
        }
    }
}
