package com.taffy.neko.models.convertor;


import com.taffy.neko.entity.User;
import com.taffy.neko.models.dto.UpdateAboutMeDTO;
import com.taffy.neko.models.dto.UserRegisterDTO;
import com.taffy.neko.models.vo.AboutMeVO;
import com.taffy.neko.models.vo.UserInfoVO;
import com.taffy.neko.models.vo.UserProfileVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {
    UserConvert INSTANT = Mappers.getMapper(UserConvert.class);

    AboutMeVO toAboutMeVO(User user);

    //DTO -> DO
    User ToUser(UpdateAboutMeDTO updateAboutMeDTO);

    //DO -> VO
    UserProfileVO toUserProFileVO(User user);

    //registerDTO -> DO
    User toUserVO(UserRegisterDTO userRegisterDTO);

    List<UserInfoVO> toUserInfoVOList(List<User> userList);
}
