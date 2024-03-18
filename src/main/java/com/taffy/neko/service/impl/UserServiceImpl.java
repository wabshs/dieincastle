package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.User;
import com.taffy.neko.mapper.UserMapper;
import com.taffy.neko.models.convertor.UserToAboutMeConvert;
import com.taffy.neko.models.dto.UpdateAboutMeDTO;
import com.taffy.neko.models.vo.AboutMeVO;
import com.taffy.neko.service.UserService;
import org.mapstruct.ap.internal.model.assignment.UpdateWrapper;
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
            return new R<>().success(200, "操作成功", aboutMeVO);
        } else {
            return new R<>().error(500, "无数据");
        }

    }

    @Override
    public R<?> updateAboutMe(UpdateAboutMeDTO reqDTO) {
        User user = UserToAboutMeConvert.INSTANT.ToUser(reqDTO);
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(User::getId, user.getId()).set(User::getAboutMe, user.getAboutMe());
        int result = userMapper.update(null, lambdaUpdateWrapper);
        if (result > 0) {
            return new R<>().success(200, "更新成功!");
        } else {
            return new R<>().error(999, "操作失败!");
        }
    }


}
