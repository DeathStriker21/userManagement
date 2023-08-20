package com.example.userManagement.service.impl;

import com.example.userManagement.entity.RolesEntity;
import com.example.userManagement.repository.RolesRepository;
import com.example.userManagement.service.RolesService;
import com.example.userManagement.service.dto.RolesRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService {

    private final RolesRepository repository;

    @Autowired
    public RolesServiceImpl(RolesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addRoles(RolesRequestDto rolesRequestDto) {
        rolesRequestDto.getRolesType().forEach(it-> {
            RolesEntity roles = new RolesEntity();
            roles.setName(it);
            repository.save(roles);
        });
    }
}
