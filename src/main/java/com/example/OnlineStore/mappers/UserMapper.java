package com.example.OnlineStore.mappers;

import com.example.OnlineStore.dto.UserDto;
import com.example.OnlineStore.entity.Users;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
//    private final ModelMapper modelMapper;
//
//    public UserMapper() {
//        this.modelMapper = new ModelMapper();
//    }
//    public UserDto mapToDto(Users users) {
//        return modelMapper.map(users, UserDto.class);
//    }

    Users mapToDto(UserDto dto);
}
