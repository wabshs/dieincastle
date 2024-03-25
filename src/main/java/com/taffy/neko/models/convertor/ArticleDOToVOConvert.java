package com.taffy.neko.models.convertor;


import com.taffy.neko.entity.Article;
import com.taffy.neko.models.vo.ArticleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleDOToVOConvert {

    ArticleDOToVOConvert INSTANCE = Mappers.getMapper(ArticleDOToVOConvert.class);

    ArticleVO toArticleVO(Article article);
}
