package com.taffy.neko.models.convertor;


import com.taffy.neko.entity.User;
import com.taffy.neko.models.dto.UpdateAboutMeDTO;
import com.taffy.neko.models.vo.AboutMeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToAboutMeConvert {
    UserToAboutMeConvert INSTANT = Mappers.getMapper(UserToAboutMeConvert.class);

    AboutMeVO toAboutMeVO(User user);

    //DTO -> DO
    User ToUser(UpdateAboutMeDTO updateAboutMeDTO);
}
