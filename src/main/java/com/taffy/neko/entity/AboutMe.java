package com.taffy.neko.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 关于我页面
 */
@Data
@TableName("about_me")
public class AboutMe {

    //用户ID
    @TableId
    private String userId;

    //关于我正文
    private String content;
}
