package com.example.userManagement.service.dto;

import com.example.userManagement.enumaration.RolesType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    String username;

    String password;

    String firstName;

    String lastName;

    RolesType rolesType;
}
