package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.User;

public interface UserService extends IService<User> {


    /**
     *
     * @param id 用户id
     * @return R.success
     */
    R<?> getUserAboutMe(String id);
}
