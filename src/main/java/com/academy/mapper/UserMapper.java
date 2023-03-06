package com.academy.mapper;

import com.academy.dto.UserDto;
import com.academy.model.User;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toModel(UserDto userDto);
    List<UserDto> toDtos(List<User> userList);
    List<User> toModels(List<UserDto> userDtoList);
}
