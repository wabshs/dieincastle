package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.User;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.UserMapper;
import com.taffy.neko.models.convertor.UserToAboutMeConvert;
import com.taffy.neko.models.dto.UpdateAboutMeDTO;
import com.taffy.neko.models.vo.AboutMeVO;
import com.taffy.neko.models.vo.UserProfileVO;
import com.taffy.neko.service.UserService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public R<?> getUserAboutMe(String id) {
        User user = userMapper.selectById(id);
        AboutMeVO aboutMeVO = UserToAboutMeConvert.INSTANT.toAboutMeVO(user);
        if (aboutMeVO.getAboutMe() != null) {
            return new R<>().success(ResponseEnum.SUCCESS, aboutMeVO);
        } else {
            return new R<>().error(ResponseEnum.ERROR);
        }

    }

    @Override
    public R<?> updateAboutMe(UpdateAboutMeDTO reqDTO) {
        //有些画蛇添足了....User类根本不需要出现,ReqDTO即可  算了就这样不改了
        User user = UserToAboutMeConvert.INSTANT.ToUser(reqDTO);
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
        UserProfileVO userProfileVO = UserToAboutMeConvert.INSTANT.toUserProFileVO(user);
        return new R<>().success(ResponseEnum.SUCCESS,userProfileVO);
    }


}
