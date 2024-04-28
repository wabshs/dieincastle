package com.taffy.neko.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taffy.neko.entity.ChatList;
import com.taffy.neko.models.vo.ChatLeftVO;

import java.util.List;

public interface ChatListMapper extends BaseMapper<ChatList> {

    List<ChatLeftVO> selectChatListById(String userId);
}
