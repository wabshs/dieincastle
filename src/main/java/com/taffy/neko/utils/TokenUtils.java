package com.taffy.neko.utils;


import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class TokenUtils {

    public static String getToken(String userId, String sign) {
        return JWT.create().withAudience(userId) //userId保存在Token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) //2h有效期
                .sign(Algorithm.HMAC256(sign)); //password作为Token秘钥
    }
}
