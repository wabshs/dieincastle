package com.taffy.neko.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Exception.ServiceException;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.User;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.mapper.UserMapper;
import com.taffy.neko.models.convertor.UserConvert;
import com.taffy.neko.models.dto.UpdateAboutMeDTO;
import com.taffy.neko.models.dto.UserLoginDTO;
import com.taffy.neko.models.dto.UserRegisterDTO;
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
        //加密成md5再存储
        user.setPassword(DigestUtil.md5Hex(reqDTO.getPassword()));
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
        String md5Pwd = DigestUtil.md5Hex(reqDTO.getPassword());
        //条件构造器
        lambdaQueryWrapper.eq(User::getUserName, reqDTO.getUserName())
                .eq(User::getPassword, md5Pwd);
        if (userMapper.selectCount(lambdaQueryWrapper) == 1) {
            return new R<>().success(ResponseEnum.SUCCESS);
        } else {
            throw new ServiceException(500, "用户名或者密码错误");
        }
    }


}
