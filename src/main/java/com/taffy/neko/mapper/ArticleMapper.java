package com.taffy.neko.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taffy.neko.entity.Article;
import com.taffy.neko.models.vo.ArticleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

    //自定义mapper使用分页
    List<ArticleVO> selectArticleVOByPage(IPage<ArticleVO> page,@Param("header") String header);

    //更新文章浏览量
    boolean updateViews(String id);

    List<Article> getArticleByTags(IPage<Article> page,@Param("tags") String tags);

    List<Article> getArticleByTagsCount(String tags);
}
