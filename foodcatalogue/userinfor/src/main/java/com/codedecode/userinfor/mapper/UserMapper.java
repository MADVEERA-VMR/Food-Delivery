package com.codedecode.userinfor.mapper;

import com.codedecode.userinfor.dto.UserDTO;
import com.codedecode.userinfor.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserDTOToUser(UserDTO userDTO);

    UserDTO mapUserToUserDTO(User user);
}
