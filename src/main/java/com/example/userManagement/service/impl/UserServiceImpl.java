package com.example.userManagement.service.impl;

import com.example.userManagement.entity.RolesEntity;
import com.example.userManagement.entity.UserEntity;
import com.example.userManagement.exception.RolesNotFoundException;
import com.example.userManagement.exception.UserDuplicateException;
import com.example.userManagement.exception.UserNotFoundException;
import com.example.userManagement.repository.RolesRepository;
import com.example.userManagement.repository.UserRepository;
import com.example.userManagement.service.UserService;
import com.example.userManagement.service.dto.UserRequestDto;
import com.example.userManagement.service.dto.UserUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RolesRepository rolesRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity getUser(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public void addUser(UserRequestDto userRequestDto) {
        if (repository.existsByUsername(userRequestDto.getUsername())) {
            throw new UserDuplicateException("User already exist");
        }

        RolesEntity rolesEntity = rolesRepository.findByName(userRequestDto.getRolesType()).orElseThrow(() -> new RolesNotFoundException("Roles not found"));

        UserEntity saveUser = new UserEntity();

        saveUser.setUsername(userRequestDto.getUsername());
        saveUser.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        saveUser.setLastName(userRequestDto.getLastName());
        saveUser.setFirstName(userRequestDto.getFirstName());
        saveUser.getRoles().add(rolesEntity);

        repository.save(saveUser);
    }

    @Override
    public void updateUser(String username, UserUpdateRequestDto userUpdateRequestDto) {
        UserEntity user = repository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        repository.save(user.update(userUpdateRequestDto));
    }

    @Override
    public void deleteUser(String username) {
        UserEntity user = repository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        repository.delete(user);
    }
}
