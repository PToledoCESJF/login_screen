package com.loginscreen.model.mapper;

import com.loginscreen.model.dto.UserListDTO;
import com.loginscreen.model.dto.UserSaveDTO;
import com.loginscreen.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserSaveDTO userSaveDTO);
    UserListDTO toDto(User user);

}
