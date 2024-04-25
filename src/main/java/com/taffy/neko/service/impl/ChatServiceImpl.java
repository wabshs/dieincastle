package com.taffy.neko.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Exception.ServiceException;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Chat;
import com.taffy.neko.entity.User;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.ChatMapper;
import com.taffy.neko.mapper.UserMapper;
import com.taffy.neko.models.vo.ChatLeftVO;
import com.taffy.neko.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

    @Resource
    private ChatMapper chatMapper;

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
        return new R<>().success(ResponseEnum.SUCCESS, chatList);
    }

    @Override
    public R<?> selectChatUserIds(String id) {
        List<String> ids = chatMapper.getOtherUserIds(id);
        return new R<>().success(ResponseEnum.SUCCESS, ids);
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
        //和该用户聊过天的用户的ID集合
        List<String> ids = chatMapper.getOtherUserIds(id);
        //把这些人的信息查出来
        List<User> users = userMapper.selectBatchIds(ids);
        List<ChatLeftVO> vos = users.stream()
                .map(user -> {
                    ChatLeftVO vo = new ChatLeftVO();
                    vo.setNickName(user.getNickName());
                    vo.setAvatarUrl(user.getAvatarUrl());
                    return vo;
                }).collect(Collectors.toList());
        return new R<>().success(ResponseEnum.SUCCESS, vos);
    }
}
