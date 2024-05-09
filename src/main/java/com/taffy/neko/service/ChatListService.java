package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.ChatList;

public interface ChatListService extends IService<ChatList> {

    R<?> createNewChat(ChatList chatList);

}
