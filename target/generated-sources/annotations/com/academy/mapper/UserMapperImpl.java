package com.academy.mapper;

import com.academy.dto.UserDto;
import com.academy.model.Product;
import com.academy.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-06T00:36:09+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );
        List<Product> list = user.getOrdini();
        if ( list != null ) {
            userDto.setOrdini( new ArrayList<Product>( list ) );
        }

        return userDto;
    }

    @Override
    public User toModel(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setName( userDto.getName() );
        user.setEmail( userDto.getEmail() );
        List<Product> list = userDto.getOrdini();
        if ( list != null ) {
            user.setOrdini( new ArrayList<Product>( list ) );
        }

        return user;
    }

    @Override
    public List<UserDto> toDtos(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userList.size() );
        for ( User user : userList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> toModels(List<UserDto> userDtoList) {
        if ( userDtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userDtoList.size() );
        for ( UserDto userDto : userDtoList ) {
            list.add( toModel( userDto ) );
        }

        return list;
    }
}
