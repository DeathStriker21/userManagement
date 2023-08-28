package com.example.userManagement.controller;

import com.example.userManagement.entity.UserEntity;
import com.example.userManagement.service.UserService;
import com.example.userManagement.service.dto.UserResponseDto;
import com.example.userManagement.service.dto.UserUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @Autowired
    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("{username}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String username) {
        UserEntity user = service.getUser(username);
        return ResponseEntity.ok(mapper.mapToUserResponseDto(user));
    }

    @PutMapping("update/{username}")
    public ResponseEntity<Void> updateUser(@PathVariable String username, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        service.updateUser(username, userUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        service.deleteUser(username);
        return ResponseEntity.ok().build();
    }

}
