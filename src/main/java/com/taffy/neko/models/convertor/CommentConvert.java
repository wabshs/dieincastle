package com.taffy.neko.models.convertor;


import com.taffy.neko.entity.Comment;
import com.taffy.neko.models.vo.CommentVO;
import com.taffy.neko.models.vo.CommentsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentConvert {
    CommentConvert INSTANCE = Mappers.getMapper(CommentConvert.class);

    //List<Comment> -> List<CommentVO>
    List<CommentVO> toCommentVOList(List<Comment> list);

    List<CommentsVO> toCommentsVO(List<Comment> list);
}
