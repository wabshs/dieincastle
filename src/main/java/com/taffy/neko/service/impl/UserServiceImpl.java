package com.taffy.neko.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.User;
import com.taffy.neko.mapper.UserMapper;
import com.taffy.neko.models.convertor.UserToAboutMeVO;
import com.taffy.neko.models.vo.AboutMeVO;
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
        AboutMeVO aboutMeVO = UserToAboutMeVO.INSTANT.toAboutMeVO(user);
        if(aboutMeVO.getAboutMe()!=null){
            return new R<>().success(200, "操作成功", aboutMeVO);
        } else {
            return new R<>().error(500, "无数据");
        }

    }
}
