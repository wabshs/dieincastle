package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.User;
import com.taffy.neko.models.dto.UpdateAboutMeDTO;

public interface UserService extends IService<User> {


    /**
     * 获取 关于我 的正文
     *
     * @param id 用户id
     * @return R.success
     */
    R<?> getUserAboutMe(String id);


    R<?> updateAboutMe(UpdateAboutMeDTO reqDTO);

    R<?> getUserProfile(String id);

}
