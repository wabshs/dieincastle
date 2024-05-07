package com.taffy.neko.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Exception.ServiceException;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Chat;
import com.taffy.neko.enums.ResponseEnum;

import com.taffy.neko.mapper.ChatListMapper;
import com.taffy.neko.mapper.ChatMapper;
import com.taffy.neko.models.vo.ChatLeftVO;
import com.taffy.neko.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

    @Resource
    private ChatListMapper chatListMapper;

    @Override
    public R<?> selectOneChat(String fromId, String toId) {
        LambdaQueryWrapper<Chat> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //select * from chat where formId = ? and toId = ? or xxx order by desc createTime
        lambdaQueryWrapper.eq(Chat::getFromId, fromId)
                .eq(Chat::getToId, toId)
                .or()
                .eq(Chat::getFromId, toId)
                .eq(Chat::getToId, fromId)
                .orderByAsc(Chat::getCreateTime);
        List<Chat> chatList = list(lambdaQueryWrapper);
        return new R<>().success(ResponseEnum.SUCCESS, chatList);
    }


    @Override
    public R<?> sendChat(Chat chat) {
        if (save(chat)) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            throw new ServiceException(ResponseEnum.ERROR);
        }
    }

    @Override
    public R<?> getChatLeft(String id) {
        List<ChatLeftVO> vos = chatListMapper.selectChatListById(id);
        return new R<>().success(ResponseEnum.SUCCESS, vos);
    }

    @Override
    public R<?> readMsg(String fromId, String toId) {
        LambdaUpdateWrapper<Chat> lambdaQueryWrapper = new LambdaUpdateWrapper<>();
        lambdaQueryWrapper.eq(Chat::getFromId, fromId)
                .eq(Chat::getToId, toId)
                .set(Chat::getIsRead, 1);
        boolean update = update(lambdaQueryWrapper);
        if (update) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            throw new ServiceException(ResponseEnum.ERROR);
        }
    }
}
