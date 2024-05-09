package com.taffy.neko.service.impl;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Exception.ServiceException;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Chat;
import com.taffy.neko.entity.ChatList;
import com.taffy.neko.entity.User;
import com.taffy.neko.enums.ResponseEnum;

import com.taffy.neko.mapper.ChatListMapper;
import com.taffy.neko.mapper.ChatMapper;
import com.taffy.neko.mapper.UserMapper;
import com.taffy.neko.models.dto.ReadMsgDTO;
import com.taffy.neko.models.vo.ChatDetailVO;
import com.taffy.neko.models.vo.ChatLeftVO;
import com.taffy.neko.service.ChatListService;
import com.taffy.neko.service.ChatService;
import com.taffy.neko.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

    @Resource
    private ChatListMapper chatListMapper;

    @Resource
    private ChatListService chatListService;

    @Resource
    private UserMapper userMapper;

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
        String fromAvatarUrl = userMapper.selectById(fromId).getAvatarUrl();
        String toAvatarUrl = userMapper.selectById(toId).getAvatarUrl();
        //转化，数据库设计的太失败的后果
        List<ChatDetailVO> chatDetailVOS = chatList.stream()
                .map(chat -> {
                    ChatDetailVO chatDetailVo = new ChatDetailVO();
                    chatDetailVo.setId(chat.getId());
                    chatDetailVo.setFromId(chat.getFromId());
                    chatDetailVo.setToId(chat.getToId());
                    chatDetailVo.setContent(chat.getContent());
                    chatDetailVo.setIsRead(chat.getIsRead());
                    chatDetailVo.setCreateTime(chat.getCreateTime());
                    chatDetailVo.setFromAvatarUrl(chat.getFromId().equals(fromId) ? fromAvatarUrl : toAvatarUrl);
                    chatDetailVo.setToAvatarUrl(chat.getToId().equals(toId) ? toAvatarUrl : fromAvatarUrl);
                    return chatDetailVo;
                })
                .collect(Collectors.toList());
        return new R<>().success(ResponseEnum.SUCCESS, chatDetailVOS);
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
    public R<?> readMsg(ReadMsgDTO reqDTO) {
        LambdaUpdateWrapper<Chat> lambdaQueryWrapper = new LambdaUpdateWrapper<>();
        lambdaQueryWrapper.eq(Chat::getFromId, reqDTO.getFromId())
                .eq(Chat::getToId, reqDTO.getToId())
                .set(Chat::getIsRead, 1);
        boolean update = update(lambdaQueryWrapper);
        if (update) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            throw new ServiceException(ResponseEnum.ERROR);
        }
    }

    @Override
    public R<?> checkChatExist(String user1, String user2) {
        LambdaQueryWrapper<Chat> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.or(wrapper -> wrapper.eq(Chat::getFromId, user1).eq(Chat::getToId, user2))
                .or(wrapper -> wrapper.eq(Chat::getFromId, user2).eq(Chat::getToId, user1));
        long count = count(lambdaQueryWrapper);
        //如果有聊天记录
        if (count > 0) {
            LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.set(ChatList::getLastTime, DateUtil.now());
            chatListService.update(lambdaUpdateWrapper);
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            //聊天记录为空则创造一个新的
            ChatList chatList = new ChatList();
            User u1 = userMapper.selectById(user1);
            User u2 = userMapper.selectById(user2);
            chatList.setToId(u1.getId());
            chatList.setFromId(u2.getId());
            chatList.setToAvatarUrl(u1.getAvatarUrl());
            chatList.setFromAvatarUrl(u2.getAvatarUrl());
            chatList.setToNickName(u1.getNickName());
            chatList.setFromNickName(u2.getNickName());
            if (chatListService.save(chatList)) {
                return new R<>().success(ResponseEnum.CHAT_LIST_EMPTY);
            } else {
                throw new ServiceException(ResponseEnum.ERROR);
            }
        }
    }
}
