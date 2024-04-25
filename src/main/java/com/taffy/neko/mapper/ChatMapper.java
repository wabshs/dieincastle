package com.taffy.neko.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taffy.neko.entity.Chat;

import java.util.List;

public interface ChatMapper extends BaseMapper<Chat> {


    /**
     * 根据用户ID查询所有和他聊过天的用户
     *
     * @param id 用户id
     * @return 用户ID集合
     */
    List<String> getOtherUserIds(String id);
}
