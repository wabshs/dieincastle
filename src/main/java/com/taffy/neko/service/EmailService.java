package com.taffy.neko.service;

import com.taffy.neko.Result.R;

public interface EmailService {


    /**
     * 通过邮箱发送验证码
     *
     * @param to       收件人
     * @param authCode 验证码自己
     * @return R
     */
    R<?> sendAuhCodeByEmail(String to, String authCode);
}
