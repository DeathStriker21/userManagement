package com.example.userManagement.controller;

import com.example.userManagement.service.RolesService;
import com.example.userManagement.service.dto.RolesRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles/")
public class RolesController {

    private final RolesService service;

    @Autowired
    public RolesController(RolesService service) {
        this.service = service;
    }

    @PostMapping("add")
    public ResponseEntity<Void> addRoles(@RequestBody RolesRequestDto rolesRequestDto) {
        service.addRoles(rolesRequestDto);
        return ResponseEntity.ok().build();
    }
}
