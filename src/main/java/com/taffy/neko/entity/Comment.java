package com.taffy.neko.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 杜昱恒
 * 评论表
 */
@Data
@TableName("comment")
public class Comment {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("评论正文")
    private String content;

    private String toCommentUserId;

    private String toCommentId;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("父id")
    private String rootId;

    @ApiModelProperty("文章id")
    private String articleId;

    @ApiModelProperty("是否删除0正常1删除")
    private Integer delFlag;

}
