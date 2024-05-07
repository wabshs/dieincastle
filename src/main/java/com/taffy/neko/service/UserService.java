package com.taffy.neko.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taffy.neko.Result.R;
import com.taffy.neko.entity.User;
import com.taffy.neko.models.dto.UpdateAboutMeDTO;
import com.taffy.neko.models.dto.UserLoginDTO;
import com.taffy.neko.models.dto.UserRegisterDTO;

public interface UserService extends IService<User> {


    /**
     * 获取 关于我 的正文
     *
     * @param id 用户id
     * @return R.success
     */
    R<?> getUserAboutMe(String id);


    /**
     * @param reqDTO 请求体
     * @return 更新关于我的内容
     */
    R<?> updateAboutMe(UpdateAboutMeDTO reqDTO);

    /**
     * @param id 用户id
     * @return 用户详细资料
     *
     */
    R<?> getUserProfile(String id);


    /**
     * @param reqDTO 注册请求体
     * @return R
     */
    R<?> userRegister(UserRegisterDTO reqDTO);

    /**
     * @param reqDTO 用户名和密码
     * @return 登录是否成功
     */
    R<?> userLogin(UserLoginDTO reqDTO);

    /**
     *
     * @param id 用户id
     * @return R
     */
    R<?> userAvatar(String id);

    R<?> getUnReadMsgNum(String id);

    R<?> getUnReadMsgNumOne(String toId, String fromId);
}
