package com.taffy.neko.models.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("评论正文")
    private String content;

    private String toCommentUserId;

    private String toCommentUserName;

    private String toCommentId;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("父id")
    private String rootId;

    @ApiModelProperty("文章id")
    private String articleId;

    @ApiModelProperty("创建人id")
    private String createBy;

    @ApiModelProperty("创建人的昵称")
    private String nickName;

    private List<CommentVO> children;
}
