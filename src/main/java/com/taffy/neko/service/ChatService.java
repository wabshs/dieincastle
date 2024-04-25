package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Chat;

public interface ChatService extends IService<Chat> {

    /**
     * 查询两个人之间的对话
     *
     * @param fromId 发送人id
     * @param toId   接收人id
     * @return R
     */
    R<?> selectOneChat(String fromId, String toId);

    /**
     * 查和某人聊过天的所有人数组
     *
     * @param id 用户ID
     * @return R(List)
     */
    R<?> selectChatUserIds(String id);

    /**
     * 创建一个聊天
     *
     * @param chat DO
     * @return R
     */
    R<?> sendChat(Chat chat);

    /**
     * 私信页面左侧展示数据
     * @return R
     */
    R<?> getChatLeft(String id);
}