package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Chat;
import com.taffy.neko.models.dto.ReadMsgDTO;

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

    R<?> readMsg(ReadMsgDTO reqDTO);

    R<?> checkChatExist(String user1, String user2);
}
