package com.taffy.neko.models.convertor;


import com.taffy.neko.entity.Article;
import com.taffy.neko.models.dto.CreateArticleDTO;
import com.taffy.neko.models.vo.ArticleDetailVO;
import com.taffy.neko.models.vo.ArticleVO;
import com.taffy.neko.models.vo.HotArticleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ArticleConvert {

    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    ArticleVO toArticleVO(Article article);

    //新建文章的reqDTO 转 文章实体类
    Article toArticleDO(CreateArticleDTO createArticleDTO);

    //文章DO转文章详情VO
    ArticleDetailVO toArticleDetailVO(Article article);

    //文章DO转热门文章VO List
    List<HotArticleVO> toHotArticleVOList(List<Article> articleList);
}
