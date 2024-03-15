package com.taffy.neko.models.convertor;


import com.taffy.neko.entity.User;
import com.taffy.neko.models.vo.AboutMeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToAboutMeVO {
    UserToAboutMeVO INSTANT = Mappers.getMapper(UserToAboutMeVO.class);

    AboutMeVO toAboutMeVO(User user);
}
