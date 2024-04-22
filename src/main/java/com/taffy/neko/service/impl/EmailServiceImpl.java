package com.taffy.neko.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.taffy.neko.Exception.ServiceException;
import com.taffy.neko.Result.R;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.service.EmailService;
import com.taffy.neko.utils.RedisCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private MailSender MailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private RedisCache redisCache;


    @Override
    public R<?> sendAuhCodeByEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        String authCode = RandomUtil.randomNumbers(6);
        //存进去 5分钟过期
        redisCache.setCacheObject(to, authCode, 5, TimeUnit.MINUTES);
        //把验证码存到redis里面后期注册的时候对比传入的验证码是否一致 Map<to,authCode> 键值对 key为userName(to)
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("智慧校园论坛");
        message.setText("【智慧校园】您的验证码为:" + authCode + " 五分钟之内有效，请勿泄露");
        try {
            MailSender.send(message);
            return new R<>().success(ResponseEnum.SUCCESS);
        } catch (Exception e) {
            throw new ServiceException(ResponseEnum.ERROR);
        }
    }
}
