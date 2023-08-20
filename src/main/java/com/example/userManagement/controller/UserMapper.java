package com.example.userManagement.controller;

import com.example.userManagement.entity.UserEntity;
import com.example.userManagement.service.dto.UserRequestDto;
import com.example.userManagement.service.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto mapToUserResponseDto(UserEntity userEntity);

}
