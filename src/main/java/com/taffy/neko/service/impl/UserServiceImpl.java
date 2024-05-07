package com.taffy.neko.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Exception.ServiceException;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.Chat;
import com.taffy.neko.entity.User;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.UserMapper;
import com.taffy.neko.models.convertor.UserConvert;
import com.taffy.neko.models.dto.UpdateAboutMeDTO;
import com.taffy.neko.models.dto.UserLoginDTO;
import com.taffy.neko.models.dto.UserRegisterDTO;
import com.taffy.neko.models.vo.AboutMeVO;
import com.taffy.neko.models.vo.AvatarAndNickNameVO;
import com.taffy.neko.models.vo.UserProfileVO;
import com.taffy.neko.service.ChatService;
import com.taffy.neko.service.UserService;
import com.taffy.neko.utils.RedisCache;
import com.taffy.neko.utils.TokenUtils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisCache redisCache;

    @Resource
    private ChatService chatService;


    @Override
    public R<?> getUserAboutMe(String id) {
        User user = userMapper.selectById(id);
        AboutMeVO aboutMeVO = UserConvert.INSTANT.toAboutMeVO(user);
        if (aboutMeVO.getAboutMe() != null) {
            return new R<>().success(ResponseEnum.SUCCESS, aboutMeVO);
        } else {
            return new R<>().error(ResponseEnum.ERROR);
        }

    }

    @Override
    public R<?> updateAboutMe(UpdateAboutMeDTO reqDTO) {
        //有些画蛇添足了....User类根本不需要出现,ReqDTO即可  算了就这样不改了
        User user = UserConvert.INSTANT.ToUser(reqDTO);
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(User::getId, user.getId()).set(User::getAboutMe, user.getAboutMe());
        int result = userMapper.update(null, lambdaUpdateWrapper);
        if (result > 0) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            return new R<>().error(ResponseEnum.ERROR);
        }
    }

    @Override
    public R<?> getUserProfile(String id) {
        User user = userMapper.selectById(id);
        UserProfileVO userProfileVO = UserConvert.INSTANT.toUserProFileVO(user);
        return new R<>().success(ResponseEnum.SUCCESS, userProfileVO);
    }

    @Override
    public R<?> userRegister(UserRegisterDTO reqDTO) {
        User user = UserConvert.INSTANT.toUserVO(reqDTO);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName, user.getUserName());
        //判断是否有重名的
        int ifSameUserName = (int) count(lambdaQueryWrapper);
        if (ifSameUserName != 0) {
            return new R<>().error(ResponseEnum.USERNAME_ALREADY_EXIST);
        }
        String authCode = reqDTO.getAuthCode();
        //把redis里存的验证码取出来
        String redisAuthCode = redisCache.getCacheObject(reqDTO.getEmail());
        if (!Objects.equals(authCode, redisAuthCode)) {
            return new R<>().error(ResponseEnum.AUTH_CODE_ERROR);
        }
        //如果正确删掉Redis的缓存
        redisCache.deleteObject(reqDTO.getEmail());
        int isInsert = userMapper.insert(user);
        if (isInsert == 1) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            return new R<>().error(ResponseEnum.ERROR);
        }
    }

    @Override
    public R<?> userLogin(UserLoginDTO reqDTO) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //条件构造器
        lambdaQueryWrapper.eq(User::getUserName, reqDTO.getUserName())
                .eq(User::getPassword, reqDTO.getPassword());
        if (userMapper.selectCount(lambdaQueryWrapper) == 1) {
            //把这个User查出来返回id
            User user = userMapper.selectOne(lambdaQueryWrapper);
            String token = TokenUtils.getToken(user.getId(), reqDTO.getPassword());
            //设置Token
            reqDTO.setToken(token);
            reqDTO.setUserId(user.getId());
            return new R<>().success(ResponseEnum.SUCCESS, reqDTO);
        } else {
            throw new ServiceException(ResponseEnum.LOGIN_ERROR);
        }
    }

    @Override
    public R<?> userAvatar(String id) {
        User user = userMapper.selectById(id);
        AvatarAndNickNameVO avatarAndNickNameVO = new AvatarAndNickNameVO();
        avatarAndNickNameVO.setAvatarUrl(user.getAvatarUrl());
        avatarAndNickNameVO.setNickName(user.getNickName());
        return new R<>().success(ResponseEnum.SUCCESS, avatarAndNickNameVO);
    }

    @Override
    public R<?> getUnReadMsgNum(String id) {
        LambdaQueryWrapper<Chat> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //接收方为“我”的id且为未读的消息
        lambdaQueryWrapper.eq(Chat::getToId, id)
                .eq(Chat::getIsRead, 0);
        long count = chatService.count(lambdaQueryWrapper);
        return new R<>().success(ResponseEnum.SUCCESS, count);
    }

    @Override
    public R<?> getUnReadMsgNumOne(String toId, String fromId) {
        LambdaQueryWrapper<Chat> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chat::getToId, toId)
                .eq(Chat::getFromId, fromId)
                .eq(Chat::getIsRead, 0);
        long count = chatService.count(lambdaQueryWrapper);
        return new R<>().success(ResponseEnum.SUCCESS, count);
    }


}
