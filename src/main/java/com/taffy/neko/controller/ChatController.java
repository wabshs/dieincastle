package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Chat;
import com.taffy.neko.entity.ChatList;
import com.taffy.neko.models.dto.ReadMsgDTO;
import com.taffy.neko.service.ChatListService;
import com.taffy.neko.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/chat")
@Api(tags = "私信模块")
public class ChatController {

    @Resource
    private ChatService chatService;

    @Resource
    private ChatListService chatListService;


    @GetMapping("/oneChat")
    @ApiOperation(value = "查询两个人所有的私信记录")
    public R<?> getOneChat(@RequestParam String fromId, @RequestParam String toId) {
        return chatService.selectOneChat(fromId, toId);
    }

    @PostMapping("/sendChat")
    @ApiOperation(value = "添加私信")
    public R<?> sendChat(@RequestBody Chat chat) {
        return chatService.sendChat(chat);
    }

    @GetMapping("/getLeftInfo")
    @ApiOperation(value = "消息记录")
    public R<?> getChatLeftInfo(@RequestParam String userId) {
        return chatService.getChatLeft(userId);
    }

    @PutMapping("/readMsg")
    @ApiOperation(value = "让私信变成已读")
    public R<?> readMsg(@RequestBody ReadMsgDTO reqDTO) {
        return chatService.readMsg(reqDTO);
    }


    @GetMapping("/checkChatIsExist")
    @ApiOperation(value = "检查两个人之间是否有私信记录")
    public R<?> checkChatIsExist(@RequestParam String user1, @RequestParam String user2) {
        return chatService.checkChatExist(user1, user2);
    }

    @PostMapping("/createNewChat")
    @ApiOperation(value = "创建新对话")
    public R<?> createNewChatInList(@RequestBody ChatList chatList) {
        return chatListService.createNewChat(chatList);
    }
}
