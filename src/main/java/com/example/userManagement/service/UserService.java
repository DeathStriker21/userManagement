package com.example.userManagement.service;

import com.example.userManagement.entity.UserEntity;
import com.example.userManagement.service.dto.UserRequestDto;
import com.example.userManagement.service.dto.UserUpdateRequestDto;

public interface UserService {
    UserEntity getUser(String username);

    void addUser(UserRequestDto userRequestDto);

    void updateUser(String username,UserUpdateRequestDto userUpdateRequestDto);

    void deleteUser(String username);
}
