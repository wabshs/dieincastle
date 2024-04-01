package com.taffy.neko.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.taffy.neko.Exception.ServiceException;
import com.taffy.neko.Result.R;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private MailSender MailSender;

    @Value("${spring.mail.username}")
    private String from;


    @Override
    public R<?> sendAuhCodeByEmail(String to, String authCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("智慧校园论坛");
        message.setText("【智慧校园】您的验证码为:" + authCode + " 五分钟之内有效，请勿泄露");
        try {
            MailSender.send(message);
            return new R<>().success(ResponseEnum.SUCCESS);
        } catch (ServiceException e) {
            throw new ServiceException(e.getCode(), e.getMessage());
        }
    }
}
